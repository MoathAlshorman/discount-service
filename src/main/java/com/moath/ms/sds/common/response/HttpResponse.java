package com.moath.ms.sds.common.response;

import java.time.Instant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Base response for all kind of Http responses.
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
@Getter
@RequiredArgsConstructor
public abstract class HttpResponse {

    protected final STATUS status;
    protected final Instant date = Instant.now();

    // Other common props would be listed here
}
