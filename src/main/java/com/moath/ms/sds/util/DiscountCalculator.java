package com.moath.ms.sds.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * A utility to calculate a discount based on the given percentage and the amount before the discount is applied.
 *
 * @author Moath.Alshorman
 * @since 28/05/2022
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DiscountCalculator {

    public static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);
    private static final DiscountCalculator INSTANCE = new DiscountCalculator();

    /**
     * Gets an instance of {@link DiscountCalculator}.
     *
     * @return a singleton instance of this class
     */
    public static DiscountCalculator of() {
        return INSTANCE;
    }

    /**
     * Calculates the net amount on the given original amount based on the given discountAmount.
     *
     * @param originalAmount amount you would like to be discounted
     * @param discountAmount total amount of discounts calculated on the given originalAmount
     * @return the net amount in a {@link BigDecimal} representation
     */
    public BigDecimal calculateNetAmount(final BigDecimal originalAmount, final BigDecimal discountAmount) {
        return originalAmount.subtract(discountAmount);
    }

    /**
     * Calculates the discount amount of the given original amount and the discount percentage.
     *
     * @param originalAmount     amount you would like to get the discount upon
     * @param discountPercentage discount in percentage format like 75.5% would be a {@link BigDecimal#valueOf(double)} with the double equals 75.5
     * @return the calculated discount amount
     */
    public BigDecimal calculateDiscountAmount(final BigDecimal originalAmount, final BigDecimal discountPercentage) {
        return originalAmount.multiply(discountPercentage).divide(ONE_HUNDRED, RoundingMode.HALF_UP);
    }
}
