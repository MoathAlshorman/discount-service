package com.moath.ms.sds.domain.discount.processor;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import com.moath.ms.sds.domain.bill.Bill;
import com.moath.ms.sds.domain.discount.DiscountProcessor;
import com.moath.ms.sds.domain.discount.overall.OverallDiscountStrategy;

/**
 * Overall discount processor.
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
@RequiredArgsConstructor
public class OverAllDiscountProcessor implements DiscountProcessor {

    private final OverallDiscountStrategy overAllDiscountStrategy;

    @Override
    public BigDecimal process(final Bill bill) {
        return overAllDiscountStrategy
            .supports(bill) ? overAllDiscountStrategy.calculateDiscount(bill) : BigDecimal.ZERO;
    }
}
