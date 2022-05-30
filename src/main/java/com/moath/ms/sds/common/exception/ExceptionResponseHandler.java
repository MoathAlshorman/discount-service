package com.moath.ms.sds.common.exception;

import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import com.moath.ms.sds.common.response.STATUS;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * All Exceptions handler.
 *
 * @author Moath.Alshorman
 * @since 29/05/2022
 */
@Slf4j
@ControllerAdvice
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
        final HttpRequestMethodNotSupportedException ex,
        final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

        StringBuilder sb = new StringBuilder();

        sb.append(ex.getMethod());
        sb.append(" method is not supported for this request. Supported methods is/are ");

        Set<HttpMethod> supportedMethods = ex.getSupportedHttpMethods();
        if (supportedMethods != null) {
            supportedMethods.forEach(t -> sb.append(t).append(" "));
        }

        return ResponseEntity.status(status)
            .body(new HttpErrorResponse(STATUS.FAILED, DefaultServiceError.GENERIC_ERROR, sb.toString()));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex,
        final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        return ResponseEntity.status(status)
            .body(new HttpErrorResponse(STATUS.FAILED, DefaultServiceError.GENERIC_ERROR, ex.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(final HttpMediaTypeNotAcceptableException ex,
        final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        return ResponseEntity.status(status)
            .body(new HttpErrorResponse(STATUS.FAILED, DefaultServiceError.GENERIC_ERROR, ex.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(final MissingPathVariableException ex,
        final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        return ResponseEntity.status(status)
            .body(new HttpErrorResponse(STATUS.FAILED, DefaultServiceError.GENERIC_ERROR, ex.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
        final MissingServletRequestParameterException ex,
        final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        return ResponseEntity.status(status)
            .body(new HttpErrorResponse(STATUS.FAILED, DefaultServiceError.GENERIC_ERROR, ex.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(final ServletRequestBindingException ex,
        final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        return ResponseEntity.status(status)
            .body(new HttpErrorResponse(STATUS.FAILED, DefaultServiceError.GENERIC_ERROR, ex.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(final ConversionNotSupportedException ex,
        final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        return ResponseEntity.status(status)
            .body(new HttpErrorResponse(STATUS.FAILED, DefaultServiceError.GENERIC_ERROR, ex.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers,
        final HttpStatus status, final WebRequest request) {
        return ResponseEntity.status(status)
            .body(new HttpErrorResponse(STATUS.FAILED, DefaultServiceError.GENERIC_ERROR, ex.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex,
        final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        return ResponseEntity.status(status)
            .body(new HttpErrorResponse(STATUS.FAILED, DefaultServiceError.GENERIC_ERROR, ex.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(final HttpMessageNotWritableException ex,
        final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        return ResponseEntity.status(status)
            .body(new HttpErrorResponse(STATUS.FAILED, DefaultServiceError.GENERIC_ERROR, ex.getMessage()));
    }

    /**
     * This method will be triggered whenever one or more fields of a bean (like the controller request) annotated
     * with @Valid violates the annotated constraint(s), the annotation might be from
     * {@link javax.validation.Constraint} package or a custom annotation constraint that validated by subtype of
     * {@link javax.validation.ConstraintValidator}.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
        HttpHeaders headers, HttpStatus status, WebRequest request) {

        final var errors = ex.getBindingResult().getFieldErrors()
            .stream()
            .map(fieldError -> fieldError.getField() + ":" + fieldError.getDefaultMessage())
            .collect(Collectors.toList());

        return ResponseEntity.status(status)
            .body(new HttpErrorResponse(STATUS.FAILED, DefaultServiceError.GENERIC_ERROR, errors));
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex,
        final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        return ResponseEntity.status(status)
            .body(new HttpErrorResponse(STATUS.FAILED, DefaultServiceError.GENERIC_ERROR, ex.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers,
        final HttpStatus status, final WebRequest request) {
        return ResponseEntity.status(status)
            .body(new HttpErrorResponse(STATUS.FAILED, DefaultServiceError.GENERIC_ERROR, ex.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex,
        final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

        return ResponseEntity.status(status)
            .body(new HttpErrorResponse(STATUS.FAILED, DefaultServiceError.GENERIC_ERROR,
                "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL()));
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(final AsyncRequestTimeoutException ex,
        final HttpHeaders headers, final HttpStatus status, final WebRequest webRequest) {
        return ResponseEntity.status(status)
            .body(new HttpErrorResponse(STATUS.FAILED, DefaultServiceError.GENERIC_ERROR, ex.getMessage()));
    }

    /**
     * {@link ServiceException} handler.
     *
     * @param ex {@link ServiceException}
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(ServiceException.class)
    ResponseEntity<HttpErrorResponse> handleServiceException(final ServiceException ex) {

        log.error("A Service Exception Occurred..", ex);
        return ResponseEntity.status(ex.getStatus())
            .body(new HttpErrorResponse(STATUS.FAILED, ex.getError(), ex.getArgs()));
    }

    /**
     * Default {@link Exception} handler.
     *
     * @param ex {@link Exception}
     * @return {@link ResponseEntity} contains Internal server response
     */
    @ExceptionHandler(Exception.class)
    ResponseEntity<HttpErrorResponse> handle(final Exception ex) {

        log.error("A Generic Exception Occurred..", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new HttpErrorResponse(STATUS.FAILED, DefaultServiceError.INTERNAL_ERROR));
    }
}