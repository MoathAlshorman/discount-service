package com.moath.ms.sds.common.exception;

import java.text.MessageFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * A common Errors for all services like the internal server error.
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
@Getter
@RequiredArgsConstructor
public enum DefaultServiceError implements ServiceError {

    INTERNAL_ERROR("WS_0000", "Internal Server Error, check again later"),
    GENERIC_ERROR("WS_0001", "Error:[{0}]"),
    RECORD_NOT_FOUND("WS_0002", "[{0}] not found with the given value [{1}]");

    private final String code;
    private final String description;
}
