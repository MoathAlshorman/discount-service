package com.moath.ms.sds.domain.discount.overall;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.RequiredArgsConstructor;
import com.moath.ms.sds.common.exception.DefaultServiceError;
import com.moath.ms.sds.common.exception.ServiceException;
import com.moath.ms.sds.domain.bill.Bill;
import com.moath.ms.sds.port.out.ThresholdDiscountConfigPort;

/**
 * A discount which is applied for any bill whose net amount is greater than 1000 of the Currency Unit.
 * <p>This type of discount depends on the total amount of the bill in the {@link BigDecimal} form.
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
@RequiredArgsConstructor
public class ThresholdDiscountStrategy implements OverallDiscountStrategy {

    private final ThresholdDiscountConfigPort thresholdDiscountConfigPort;

    @Override
    public boolean supports(final Bill bill) {
        return bill.getNetAmount().compareTo(getOverallDiscountConfig().getThresholdAmount()) >= 0;
    }

    @Override
    public BigDecimal calculateDiscount(final Bill bill) {
        return getOverallDiscountConfig().getDiscountAmountPerThreshold()
            .multiply(BigDecimal.valueOf(
                bill.getNetAmount().divide(getOverallDiscountConfig().getThresholdAmount(), RoundingMode.DOWN).intValue()));
    }

    /**
     * Gets the overall configurations.
     *
     * @return the configuration if available, otherwise throws internal error.
     */
    private OverallDiscountConfig getOverallDiscountConfig() {
        return thresholdDiscountConfigPort.findAllThresholdDiscountConfigs()
            .stream()
            .findFirst()
            .orElseThrow(() -> ServiceException.internalError(DefaultServiceError.INTERNAL_ERROR));
    }
}
