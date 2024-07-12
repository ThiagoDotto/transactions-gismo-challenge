package com.pismo.resource.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import jakarta.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class TransactionExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionExceptionHandler.class);

    @ExceptionHandler(value = {PismoException.class})
    protected ResponseEntity<Object> handlerPrescricaoBeneficiario(PismoException ex, WebRequest request) {
        return buildResponseEntity(ApiError.createError(ex));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {

        long hoje = new Date().getTime();
        List<ApiErrorItem> apiErrorItems = ex.getBindingResult().getAllErrors().stream()
                .map(error -> new ApiErrorItem("", error.getDefaultMessage(), getPropertyPath(error), hoje))
                .collect(Collectors.toList());

        return buildResponseEntity(ApiError.createBeanValidationError(apiErrorItems));
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatusCode status, WebRequest request) {
        LOGGER.error(ex.getMessage(), ex);
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> onConstraintValidationException(ConstraintViolationException ex) {
        return buildResponseEntity(ApiError.createBeanValidationError(ex));
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handler(Exception ex, WebRequest request) {
        LOGGER.error(ex.getMessage(), ex);
        return buildResponseEntity(ApiError.createInternalError(ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }

    private static String getPropertyPath(ObjectError error) {
        String propertyPath = error.getObjectName();

        Object[] argument = error.getArguments();
        if (argument != null && argument.length > 0) {
            DefaultMessageSourceResolvable defaultMessage = (DefaultMessageSourceResolvable) argument[0];

            if (defaultMessage != null) {
                String[] codes = defaultMessage.getCodes();
                if (codes != null && codes.length > 0) {
                    propertyPath = codes[0];
                }
            }
        }
        return propertyPath;
    }

}
