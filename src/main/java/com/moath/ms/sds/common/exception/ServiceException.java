package com.moath.ms.sds.common.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * A common place for all exceptions in the service
 * which is constructed in a way so the {@link ExceptionResponseHandler} can translate it a machine/human Http response.
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
@Getter
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class ServiceException extends RuntimeException {

    private final HttpStatus status;
    private final ServiceError error;
    private final transient Object[] args;

    /**
     * Constructs a bad request exception.
     *
     * @param error describes the bad request exception
     * @return a {@link ServiceException}
     */
    public static ServiceException badRequest(final ServiceError error, Object... args) {
        return new ServiceException(HttpStatus.BAD_REQUEST, error, args);
    }

    /**
     * Constructs a internalError exception.
     *
     * @param error describes the bad request exception
     * @return a {@link ServiceException}
     */
    public static ServiceException internalError(final ServiceError error, Object... args) {
        return new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, error, args);
    }

    @Override
    public String getMessage() {
        return String.format("Error code [%s], http status [%s]", error.getCode(), status.toString());
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
