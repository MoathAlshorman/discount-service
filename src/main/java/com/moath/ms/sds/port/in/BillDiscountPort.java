package com.moath.ms.sds.port.in;

import java.math.BigDecimal;
import com.moath.ms.sds.domain.bill.Bill;

/**
 * An input port for calculating the net amount of a given bill.
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
public interface BillDiscountPort {

    /**
     * Calculates the net amount of the given bill after applying all of the applicable discounts.
     *
     * @param bill the bill would like to be calculated
     * @return the net amount of the bill as {@link BigDecimal}
     */
    BigDecimal calculateNetPayableAmount(final Bill bill);
}
