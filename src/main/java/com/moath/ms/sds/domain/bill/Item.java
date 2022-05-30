package com.moath.ms.sds.domain.bill;

import java.math.BigDecimal;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * An item with supported types in the given bill.
 *
 * @author Moath.Alshorman
 * @since 28/05/2022
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Item {

    @NotNull
    private final ItemType type;

    @NotBlank
    private final String name;

    @NotNull
    @Digits(integer = 12, fraction = 3)
    private final BigDecimal price;
}
