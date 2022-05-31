package com.moath.ms.sds.adapter.in.rest;

import java.math.BigDecimal;
import lombok.Getter;
import com.moath.ms.sds.common.response.HttpResponse;
import com.moath.ms.sds.common.response.STATUS;
import com.moath.ms.sds.domain.bill.Currency;

/**
 * The response of the discount calculation on a given bill.
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
@Getter
public class NetAmountResponse extends HttpResponse {

    private final BigDecimal netPayableAmount;
    private final Currency currency;

    /**
     * Args constructor.
     *
     * @param netPayableAmount after the discounts
     * @param currency of the total amount
     */
    public NetAmountResponse(final BigDecimal netPayableAmount, final Currency currency) {
        super(STATUS.SUCCESS);
        this.netPayableAmount = netPayableAmount;
        this.currency = currency;
    }
}
