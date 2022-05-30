package com.moath.ms.sds.domain.discount;

import java.math.BigDecimal;
import java.util.List;
import com.moath.ms.sds.domain.bill.Bill;
import com.moath.ms.sds.domain.discount.overall.OverallDiscountConfig;
import com.moath.ms.sds.domain.discount.overall.ThresholdDiscountStrategy;
import com.moath.ms.sds.port.out.ThresholdDiscountConfigPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OverallDiscountStrategyTest {

    @Test
    void testGivenValidAmount_thenSupportedEqualsTrue() {

        final var overallDiscountConfigPort = getOverallDiscountConfigPort(BigDecimal.valueOf(1000), null);
        Assertions.assertTrue(new ThresholdDiscountStrategy(overallDiscountConfigPort).supports(createBill(BigDecimal.valueOf(2000))));
    }

    @Test
    void testGivenInValidAmount_thenSupportedEqualsTrue() {
        final var overallDiscountConfigPort = getOverallDiscountConfigPort(BigDecimal.valueOf(4000), null);
        Assertions
            .assertFalse(new ThresholdDiscountStrategy(overallDiscountConfigPort).supports(createBill(BigDecimal.valueOf(2000))));
    }


    @Test
    void testGivenBillNetAmount_thenAppliedDiscount() {

        final ThresholdDiscountConfigPort thresholdDiscountConfigPort = getOverallDiscountConfigPort(BigDecimal.valueOf(1000),
            BigDecimal.valueOf(2.5));

        final var netAmount = BigDecimal.valueOf(4000);
        final BigDecimal appliedDiscount = new ThresholdDiscountStrategy(thresholdDiscountConfigPort)
            .calculateDiscount(createBill(netAmount));

        Assertions.assertEquals(BigDecimal.valueOf(10.0), appliedDiscount);
    }

    private ThresholdDiscountConfigPort getOverallDiscountConfigPort(final BigDecimal thresholdAmount, final BigDecimal discountAmountPerThreshold) {
        return () -> List.of(new OverallDiscountConfig(thresholdAmount, discountAmountPerThreshold));
    }


    private Bill createBill(final BigDecimal netAmount) {
        return new Bill(null, null, null, netAmount, null);
    }
}