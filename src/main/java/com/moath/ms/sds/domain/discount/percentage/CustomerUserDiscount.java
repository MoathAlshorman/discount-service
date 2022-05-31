package com.moath.ms.sds.domain.discount.percentage;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import com.moath.ms.sds.domain.bill.Bill;
import com.moath.ms.sds.domain.bill.PurchaserType;
import com.moath.ms.sds.port.out.PercentageDiscountConfigPort;
import com.moath.ms.sds.port.out.UserPort;

/**
 * A percentage discount that applied on {@link PurchaserType#CUSTOMER} Purchaser.
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
public class CustomerUserDiscount extends PercentageBasedDiscount {

    /**
     * As this might not be changed often, we can keep it here.
     * If we notice a frequent change then would be moved to a dynamic config like a DB.
     */
    public static final int CONSIDER_PERMANENT_CUSTOMER = 2;
    public static final ZoneId UTC = ZoneId.of("UTC");

    /**
     * Args constructor to inject the required beans.
     *
     * @param discountConfigsPort to find the configured discount
     */
    public CustomerUserDiscount(final PercentageDiscountConfigPort discountConfigsPort, final UserPort userPort) {
        super(discountConfigsPort, userPort);
    }

    @Override
    public boolean supports(final Bill bill) {
        final var purchaser = findPurchaser(bill.getPurchaserId());
        final var permanentCustomer =
            ChronoUnit.YEARS.between(
                LocalDate.ofInstant(purchaser.getRegisteredAt(), UTC),
                LocalDate.ofInstant(bill.getPurchasedAt(), UTC))
                >= CONSIDER_PERMANENT_CUSTOMER;
        return purchaser.getType() == getUserType() && permanentCustomer;
    }

    @Override
    protected PurchaserType getUserType() {
        return PurchaserType.CUSTOMER;
    }
}
