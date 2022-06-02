package com.moath.ms.sds.domain.usecase;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import com.moath.ms.sds.common.exception.DefaultServiceError;
import com.moath.ms.sds.common.exception.ServiceError;
import com.moath.ms.sds.common.exception.ServiceException;
import com.moath.ms.sds.domain.bill.Bill;
import com.moath.ms.sds.domain.bill.Currency;
import com.moath.ms.sds.domain.bill.Item;
import com.moath.ms.sds.domain.bill.ItemType;
import com.moath.ms.sds.domain.bill.Purchaser;
import com.moath.ms.sds.domain.bill.PurchaserType;
import com.moath.ms.sds.domain.discount.DiscountProcessor;
import com.moath.ms.sds.domain.discount.overall.OverallDiscountConfig;
import com.moath.ms.sds.domain.discount.overall.ThresholdDiscountStrategy;
import com.moath.ms.sds.domain.discount.percentage.AffiliateUserDiscount;
import com.moath.ms.sds.domain.discount.percentage.CustomerUserDiscount;
import com.moath.ms.sds.domain.discount.percentage.EmployeeUserDiscount;
import com.moath.ms.sds.domain.discount.percentage.PercentageBasedDiscount;
import com.moath.ms.sds.domain.discount.processor.OverAllDiscountProcessor;
import com.moath.ms.sds.domain.discount.processor.PercentageDiscountProcessor;
import com.moath.ms.sds.domain.discount.processor.TotalNetAmountCalculatorProcessor;
import com.moath.ms.sds.port.out.PercentageDiscountConfigPort;
import com.moath.ms.sds.port.out.ThresholdDiscountConfigPort;
import com.moath.ms.sds.port.out.UserPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

/**
 * A unit test (the unit here is the business logic) to comply wit the bellow constraints.
 * <p>
 * Provide logic for a Store Discounts System. On a retail website, the following discounts apply:
 * 1. If the user is an employee of the store, he gets a 20% discount.
 * 2. If the user is an affiliate of the store, he gets a 5% discount.
 * 3. If the user has been a customer for over 2 years, he gets a 2% discount.
 * 4. For every 1000 SAR on the bill, there would be a 25 SAR discount (e.g. for 1000 SAR, you
 * get 25 SAR as a discount).
 * 5. The percentage based discounts do not apply on groceries.
 * 6. A user can get only one of the percentage based discounts on a bill.
 *
 * @author Moath.Alshorman
 * @since 6/2/2022
 */
class BillDiscountUseCaseTest {

    private final UserPort userPort = Mockito.mock(UserPort.class);
    private final PercentageDiscountConfigPort discountConfigPort = Mockito.mock(PercentageDiscountConfigPort.class);
    private final ThresholdDiscountConfigPort thresholdDiscountConfigPort = Mockito.mock(ThresholdDiscountConfigPort.class);


    private final List<PercentageBasedDiscount> discountsStrategies = List.of(
        new EmployeeUserDiscount(discountConfigPort, userPort),
        new AffiliateUserDiscount(discountConfigPort, userPort),
        new CustomerUserDiscount(discountConfigPort, userPort));

    private final List<DiscountProcessor> allDiscountProcessors = List.of(
        new PercentageDiscountProcessor(discountsStrategies),
        new OverAllDiscountProcessor(new ThresholdDiscountStrategy(thresholdDiscountConfigPort))
    );

    private final BillDiscountUseCase billDiscountUseCase = new BillDiscountUseCase(new TotalNetAmountCalculatorProcessor(allDiscountProcessors));

    //1. If the user is an employee of the store, he gets a 20% discount.
    @Test
    void givenEmployeeUser_whenCalculateDiscounts_then20PercentDiscounts() {

        //given
        configureDiscounts(
            PurchaserType.EMPLOYEE,
            BigDecimal.valueOf(20.0),
            BigDecimal.valueOf(1000),
            BigDecimal.valueOf(25),
            Instant.parse("2021-12-03T10:15:30.00Z"));

        final Bill expensivePotatoBill = new Bill(
            Instant.now(),
            "moath.alshorman@hotmail.com",
            List.of(new Item(ItemType.OTHER, "cheep stuff!", BigDecimal.valueOf(100))),
            BigDecimal.valueOf(100),
            Currency.SAR);

        //when
        final BigDecimal netPayableAmount = billDiscountUseCase.calculateNetPayableAmount(expensivePotatoBill);

        //then
        assertEquals(BigDecimal.valueOf(80).setScale(3, RoundingMode.HALF_UP), netPayableAmount);

    }

    //2. If the user is an affiliate of the store, he gets a 5% discount.
    @Test
    void givenAffiliateUser_whenCalculateDiscounts_then5PercentDiscounts() {

        //given
        configureDiscounts(
            PurchaserType.AFFILIATE,
            BigDecimal.valueOf(5.0),
            BigDecimal.valueOf(1000),
            BigDecimal.valueOf(25),
            Instant.parse("2021-12-03T10:15:30.00Z"));

        final Bill expensivePotatoBill = new Bill(
            Instant.now(),
            "moath.alshorman@hotmail.com",
            List.of(new Item(ItemType.OTHER, "cheep stuff!", BigDecimal.valueOf(100))),
            BigDecimal.valueOf(100),
            Currency.SAR);

        //when
        final BigDecimal netPayableAmount = billDiscountUseCase.calculateNetPayableAmount(expensivePotatoBill);

        //then
        assertEquals(BigDecimal.valueOf(95).setScale(3, RoundingMode.HALF_UP), netPayableAmount);
    }

    //3. If the user has been a customer for over 2 years, he gets a 2% discount.
    @Test
    void givenOldRegisteredCustomer_whenCalculateDiscounts_then2PercentageDiscounts() {

        //given
        configureDiscounts(
            PurchaserType.CUSTOMER,
            BigDecimal.valueOf(2.0),
            BigDecimal.valueOf(1000),
            BigDecimal.valueOf(25),
            Instant.now().minus(365 * 2, ChronoUnit.DAYS));

        final Bill expensivePotatoBill = new Bill(
            Instant.now(),
            "moath.alshorman@hotmail.com",
            List.of(new Item(ItemType.OTHER, "delicious food!", BigDecimal.valueOf(100))),
            BigDecimal.valueOf(100),
            Currency.SAR);

        //when
        final BigDecimal netPayableAmount = billDiscountUseCase.calculateNetPayableAmount(expensivePotatoBill);

        //then
        assertEquals(BigDecimal.valueOf(98).setScale(3, RoundingMode.HALF_UP), netPayableAmount);
    }

    //3.1. !(If the user has been a customer for over 2 years, he gets a 2% discount).
    @Test
    void givenNewRegisteredCustomer_whenCalculateDiscounts_thenNoPercentageDiscounts() {

        //given
        configureDiscounts(
            PurchaserType.CUSTOMER,
            BigDecimal.valueOf(2.0),
            BigDecimal.valueOf(1000),
            BigDecimal.valueOf(25),
            Instant.now().minus(365 * 2 - 1, ChronoUnit.DAYS));

        final Bill expensivePotatoBill = new Bill(
            Instant.now(),
            "moath.alshorman@hotmail.com",
            List.of(new Item(ItemType.OTHER, "delicious food!", BigDecimal.valueOf(100))),
            BigDecimal.valueOf(100),
            Currency.SAR);

        //when
        final BigDecimal netPayableAmount = billDiscountUseCase.calculateNetPayableAmount(expensivePotatoBill);

        //then
        assertEquals(BigDecimal.valueOf(100).setScale(3, RoundingMode.HALF_UP), netPayableAmount);
    }

    // 4. For every 1000 SAR on the bill, there would be a 25 SAR discount
    // (e.g. for 1000 SAR, you get 25 SAR as a discount).
    @Test
    void givenMoreThan4000SAR_whenCalculateDiscounts_then100SARDiscounts() {

        //given
        configureDiscounts(
            PurchaserType.NOT_APPLICABLE,
            BigDecimal.valueOf(2.0),
            BigDecimal.valueOf(1000),//for 1000 SAR
            BigDecimal.valueOf(25),//you get 25 SAR as a discount
            Instant.now());

        final Bill expensivePotatoBill = new Bill(
            Instant.now(),
            "moath.alshorman@hotmail.com",
            List.of(
                new Item(ItemType.OTHER, "Good stuff!", BigDecimal.valueOf(2000)),
                new Item(ItemType.OTHER, "Samsung Phone!", BigDecimal.valueOf(2000))),
            BigDecimal.valueOf(4000),
            Currency.SAR);

        //when
        final BigDecimal netPayableAmount = billDiscountUseCase.calculateNetPayableAmount(expensivePotatoBill);

        //then
        assertEquals(BigDecimal.valueOf(3900).setScale(3, RoundingMode.HALF_UP), netPayableAmount);
    }

    //5. The percentage based discounts do not apply on groceries.
    @Test
    void givenOnlyGroceries_whenCalculateDiscounts_thenNoPercentageDiscounts() {

        //given
        configureDiscounts(
            PurchaserType.AFFILIATE,
            BigDecimal.valueOf(2.0),
            BigDecimal.valueOf(1000),
            BigDecimal.valueOf(25),
            Instant.now());

        final Bill expensivePotatoBill = new Bill(
            Instant.now(),
            "moath.alshorman@hotmail.com",
            List.of(new Item(ItemType.GROCERIES, "potato", BigDecimal.valueOf(10))),
            BigDecimal.valueOf(10),
            Currency.SAR);

        //when
        final BigDecimal netPayableAmount = billDiscountUseCase.calculateNetPayableAmount(expensivePotatoBill);

        //then
        assertEquals(BigDecimal.valueOf(10).setScale(3, RoundingMode.HALF_UP), netPayableAmount);
    }

    //User is not found
    @Test
    void givenNotExistingUser_whenCalculateDiscounts_thenNoRecordFoundException() {

        //given
        configureDiscounts(
            PurchaserType.AFFILIATE,
            BigDecimal.valueOf(2.0),
            BigDecimal.valueOf(1000),
            BigDecimal.valueOf(25),
            Instant.now());

        final Bill expensivePotatoBill = new Bill(
            Instant.now(),
            "notFoundUser@notFound.com",
            List.of(new Item(ItemType.GROCERIES, "potato", BigDecimal.valueOf(10))),
            BigDecimal.valueOf(10),
            Currency.SAR);

        //when
        final Executable netPayableAmountExecutable = () -> billDiscountUseCase.calculateNetPayableAmount(expensivePotatoBill);

        //then
        final ServiceException expectedNotFoundException =
            ServiceException.badRequest(DefaultServiceError.RECORD_NOT_FOUND, "purchaserId", "notFoundUser@notFound.com");

        final ServiceException actualException = assertThrows(expectedNotFoundException.getClass(), netPayableAmountExecutable);
        Assertions.assertEquals(expectedNotFoundException, actualException);
    }

    private void configureDiscounts(
        final PurchaserType purchaserType,
        final BigDecimal percentageDiscount,
        final BigDecimal thresholdAmount,
        final BigDecimal discountAmountPerThreshold,
        final Instant userRegisteredAt) {

        BDDMockito.given(discountConfigPort.findPercentageDiscount(purchaserType))
            .willReturn(percentageDiscount);
        BDDMockito.given(thresholdDiscountConfigPort.findAllThresholdDiscountConfigs())
            .willReturn(List.of(new OverallDiscountConfig(thresholdAmount, discountAmountPerThreshold)));

        BDDMockito.given(userPort.findUser("moath.alshorman@hotmail.com"))
            .willReturn(Optional.of(Purchaser.builder()
                .email("moath.alshorman@hotmail.com")
                .registeredAt(userRegisteredAt)
                .type(purchaserType)
                .build()));
    }
}