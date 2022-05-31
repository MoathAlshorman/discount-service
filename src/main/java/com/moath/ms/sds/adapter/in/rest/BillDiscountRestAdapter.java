package com.moath.ms.sds.adapter.in.rest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import com.moath.ms.sds.domain.bill.Currency;
import com.moath.ms.sds.port.in.BillDiscountPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Discounts Rest controller adapter.
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
@RequestMapping("discounts")
@RestController
@RequiredArgsConstructor
public class BillDiscountRestAdapter {

    private final BillDiscountPort billDiscountPort;
    private final BillMapper mapper;

    /**
     * A POST endpoint to calculate and returns the net payable amount on the given bill.
     *
     * @param bill on which the calculation would be applied
     * @return the net payable amount
     */
    @PostMapping("payable-amount")
    public ResponseEntity<NetAmountResponse> calculateNetPayableAmount(@RequestBody @Valid @NotNull final BillDiscountRequest bill) {
        return ResponseEntity.ok(new NetAmountResponse(billDiscountPort.calculateNetPayableAmount(mapper.map(bill)), Currency.SAR));
    }
}
