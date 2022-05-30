package com.moath.ms.sds.port.out;

import java.math.BigDecimal;
import com.moath.ms.sds.domain.bill.PurchaserType;

/**
 * Percentage Discounts related configurations.
 *
 * @author Moath.Alshorman
 * @since 28/05/2022
 */
public interface PercentageDiscountConfigPort {

    /**
     * Finds the configured percentage for the given purchaserType.
     *
     * @param purchaserType for which a discount percentage is returned
     * @return discount percentage if any, otherwise {@link BigDecimal#ZERO} would be returned
     */
    BigDecimal findPercentageDiscount(final PurchaserType purchaserType);
}
