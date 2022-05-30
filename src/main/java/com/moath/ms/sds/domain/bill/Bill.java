package com.moath.ms.sds.domain.bill;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Bill Domain Business Object which is the core of the service.
 *
 * @author Moath.Alshorman
 * @since 30/05/2022
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class Bill {

    private final Instant purchasedAt;
    private final String purchaserId;
    private final List<Item> items;
    private final BigDecimal netAmount;
    private final Currency currency;
}
