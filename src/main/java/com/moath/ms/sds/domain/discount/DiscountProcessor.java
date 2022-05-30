package com.moath.ms.sds.domain.discount;

import java.math.BigDecimal;
import com.moath.ms.sds.domain.bill.Bill;

/**
 * The logic of calculating total discounts is encapsulated by this processor.
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
public interface DiscountProcessor {

    /**
     * Processes the given bill to find total amount of discounts.
     *
     * @param bill on which the desired discounts would be applied
     * @return total amount of discount on the given bill as {@link BigDecimal}
     */
    BigDecimal process(final Bill bill);
}
