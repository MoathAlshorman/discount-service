package com.moath.ms.sds.domain.usecase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.RequiredArgsConstructor;
import com.moath.ms.sds.common.annotation.UseCase;
import com.moath.ms.sds.domain.bill.Bill;
import com.moath.ms.sds.domain.discount.DiscountProcessor;
import com.moath.ms.sds.port.in.BillDiscountPort;

/**
 * Bill Discount use cases business logic.
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
@UseCase
@RequiredArgsConstructor
public class BillDiscountUseCase implements BillDiscountPort {

    private final DiscountProcessor discountProcessor;

    @Override
    public BigDecimal calculateNetPayableAmount(final Bill bill) {
        final var netAmount = discountProcessor.process(bill);
        return netAmount.setScale(3, RoundingMode.HALF_UP);
    }
}
