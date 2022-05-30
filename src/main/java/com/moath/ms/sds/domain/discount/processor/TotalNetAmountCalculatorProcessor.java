package com.moath.ms.sds.domain.discount.processor;

import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.moath.ms.sds.util.DiscountCalculator;
import com.moath.ms.sds.domain.bill.Bill;
import com.moath.ms.sds.domain.discount.DiscountProcessor;

/**
 * The main processor which the client interacts with to get the total net amount.
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
@Getter
@RequiredArgsConstructor
public class TotalNetAmountCalculatorProcessor implements DiscountProcessor {

    private final List<DiscountProcessor> processors;

    @Override
    public BigDecimal process(final Bill bill) {

        final var totalDiscounts = processors.stream()
            .map(discountProcessor -> discountProcessor.process(bill))
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        return DiscountCalculator.of().calculateNetAmount(bill.getNetAmount(), totalDiscounts);
    }
}
