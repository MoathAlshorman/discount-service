package com.moath.ms.sds.domain.discount.processor;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import com.moath.ms.sds.domain.bill.Bill;
import com.moath.ms.sds.domain.discount.DiscountProcessor;
import com.moath.ms.sds.domain.discount.percentage.PercentageBasedDiscount;
import com.moath.ms.sds.domain.discount.percentage.ZeroPercentageDiscount;

/**
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
@RequiredArgsConstructor
public class PercentageDiscountProcessor implements DiscountProcessor {

    private final List<PercentageBasedDiscount> basedDiscountList;

    @Override
    public BigDecimal process(final Bill bill) {
        return basedDiscountList.stream()
            .filter(discount -> discount.supports(bill))
            .findFirst()
            .orElse(new ZeroPercentageDiscount())
            .calculateDiscount(bill);
    }
}
