package com.moath.ms.sds.adapter.in;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import com.moath.ms.sds.domain.bill.Currency;
import com.moath.ms.sds.domain.bill.Item;

/**
 * Bill discount request model.
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class BillDiscountRequest {

    @NotNull
    private final Instant purchasedAt;

    @NotBlank
    @Email
    private final String purchaserId;

    @Valid
    @NotEmpty
    private final List<Item> items;

    @Digits(integer = 12, fraction = 3)
    private final BigDecimal netAmount;

    @NotNull
    private final Currency currency;
}
