package com.moath.ms.sds.common.exception;

/**
 * Error interface to allow services to own their errors.
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
public interface ServiceError {

    /**
     * Gets Error Code.
     *
     * @return code as String
     */
    String getCode();

    /**
     * Gets Error description.
     *
     * @return description as String
     */
    String getDescription();
}
