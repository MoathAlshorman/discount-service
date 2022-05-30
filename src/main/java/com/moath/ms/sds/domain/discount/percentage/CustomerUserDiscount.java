package com.moath.ms.sds.domain.discount.percentage;

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
     * Args constructor to inject the required beans.
     *
     * @param discountConfigsPort to find the configured discount
     */
    public CustomerUserDiscount(final PercentageDiscountConfigPort discountConfigsPort, final UserPort userPort) {
        super(discountConfigsPort, userPort);
    }

    @Override
    protected PurchaserType getUserType() {
        return PurchaserType.CUSTOMER;
    }
}
