package com.moath.ms.sds.common.exception;

import lombok.Getter;
import com.moath.ms.sds.common.response.HttpResponse;
import com.moath.ms.sds.common.response.STATUS;
import com.moath.ms.sds.util.MessageFormatter;

/**
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
@Getter
public class HttpErrorResponse extends HttpResponse {

    private final String code;
    private final String description;

    public HttpErrorResponse(final STATUS status, final ServiceError error, final Object... args) {
        super(status);
        this.code = error.getCode();
        this.description = MessageFormatter.of().format(error.getDescription(), args);
    }
}
