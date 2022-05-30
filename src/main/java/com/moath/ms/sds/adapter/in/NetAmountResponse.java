package com.moath.ms.sds.adapter.in;

import java.math.BigDecimal;
import lombok.Getter;
import com.moath.ms.sds.common.response.HttpResponse;
import com.moath.ms.sds.common.response.STATUS;

/**
 * The response of the discount calculation on a given bill.
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
@Getter
public class NetAmountResponse extends HttpResponse {

    private final BigDecimal netPayableAmount;

    public NetAmountResponse(final BigDecimal netPayableAmount) {
        super(STATUS.SUCCESS);
        this.netPayableAmount = netPayableAmount;
    }
}
