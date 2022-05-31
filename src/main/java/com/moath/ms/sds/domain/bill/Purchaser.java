package com.moath.ms.sds.domain.bill;

import java.time.Instant;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class Purchaser {

    private final String email;
    private final PurchaserType type;
    private final Instant registeredAt;
}
