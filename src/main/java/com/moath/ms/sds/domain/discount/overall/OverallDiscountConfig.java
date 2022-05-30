package com.moath.ms.sds.domain.discount.overall;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Overall discount configuration.
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
@Getter
@RequiredArgsConstructor
public class OverallDiscountConfig {

    private final BigDecimal thresholdAmount;
    private final BigDecimal discountAmountPerThreshold;

    /**
     * Finds the configured threshold amount for overall discount
     * so you can validate the bill whether it's applicable for this type of discount.
     *
     * @return threshold amount in {@link BigDecimal} form
     */
    BigDecimal getThresholdAmount() {
        return thresholdAmount;
    }

    /**
     * Finds the discount to applied on every amount in the bill that equals the threshold amount.
     *
     * @return discount amount in {@link BigDecimal} form
     */
    BigDecimal getDiscountAmountPerThreshold() {
        return discountAmountPerThreshold;
    }
}
