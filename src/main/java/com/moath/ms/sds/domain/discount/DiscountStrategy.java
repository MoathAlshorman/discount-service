package com.moath.ms.sds.domain.discount;

import java.math.BigDecimal;

/**
 * All of application's strategies should implement this class to make sure for which T it should be applied.
 *
 * @author Moath.Alshorman
 * @since 28/05/2022
 */
public interface DiscountStrategy<T> {

    /**
     * Decides if the given type is supported or not.
     *
     * @param bill the type of bill you would like to apply this strategy upon
     * @return whether the given type is supported
     */
    boolean supports(final T bill);

    /**
     * Calculates discount amount after applying the right discount on the given bill
     * based on the user who purchased the items.
     *
     * @param bill the bill you would like to calculate its discount
     * @return the discount amount after applying the discount percentage
     */
    BigDecimal calculateDiscount(final T bill);
}
