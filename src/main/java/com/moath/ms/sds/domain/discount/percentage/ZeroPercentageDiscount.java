package com.moath.ms.sds.domain.discount.percentage;

import java.math.BigDecimal;
import com.moath.ms.sds.domain.bill.PurchaserType;

/**
 * Used when no percentage discounts found.
 *
 * @author Moath.Alshorman
 * @since 31/05/2022
 */
public class ZeroPercentageDiscount extends PercentageBasedDiscount {

    /**
     * No Args constructor.
     */
    public ZeroPercentageDiscount() {
        super(null, null);
    }

    @Override
    protected BigDecimal getDiscountPercentage() {
        return BigDecimal.ZERO;
    }

    @Override
    protected PurchaserType getUserType() {
        return PurchaserType.NOT_APPLICABLE;
    }
}
