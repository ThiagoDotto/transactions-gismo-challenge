package com.pismo.resource.exception.apierror;

import com.pismo.resource.exception.PismoException;
import com.pismo.resource.exception.PismoUtilError;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class ApiError {

    private String id;
    private String code;
    private String message;
    private String owner;
    private Long date;
    private List<ApiErrorItem> arguments;

    private Integer status;

    private ApiError(Response.Status status) {
        this.status = status.getStatusCode();
        this.message = status.getReasonPhrase();
        this.code = "" + status.getStatusCode();
        this.owner = "";
        this.date = new Date().getTime();
        this.arguments = new ArrayList<>();
    }

    private static ApiError createDefaultApiValidationError() {
        return new ApiError(Response.Status.BAD_REQUEST);
    }

    private static ApiError createDefaultApiInternalError() {
        return new ApiError(Response.Status.INTERNAL_SERVER_ERROR);
    }

    private static ApiError createCustomApiError(Response.Status status) {
        return new ApiError(status);
    }

    public static ApiError createBeanValidationError(ConstraintViolationException ex) {
        long hoje = new Date().getTime();
        ApiError apiError = createDefaultApiValidationError();
        for (ConstraintViolation error : ex.getConstraintViolations()) {
            apiError.getArguments().add(new ApiErrorItem("", error.getMessage(), error.getPropertyPath().toString(), hoje));
        }
        return apiError;
    }

    public static ApiError createBeanValidationError(List<ApiErrorItem> errosItens) {
        ApiError apiError = createDefaultApiValidationError();
        apiError.getArguments().addAll(errosItens);
        return apiError;
    }

    public static ApiError createBadRequest(String message) {
        ApiError apiError = createDefaultApiValidationError();
        apiError.getArguments().add(new ApiErrorItem("", message, "", new Date().getTime()));
        return apiError;
    }

    public static ApiError createInternalError(Exception exception) {
        ApiError apiError = createDefaultApiInternalError();
        apiError.getArguments().add(new ApiErrorItem("", exception.getMessage(), "", new Date().getTime()));
        return apiError;
    }

    public static ApiError createInternalError(String message) {
        ApiError apiError = createDefaultApiInternalError();
        apiError.getArguments().add(new ApiErrorItem("", message, "", new Date().getTime()));
        return apiError;
    }

    public static ApiError createError(PismoException pismoException) {
        ApiError apiError = createCustomApiError(pismoException.getPismoUtilError().getStatusCode());
        PismoUtilError pismoUtilError = pismoException.getPismoUtilError();
        apiError.getArguments().add(new ApiErrorItem(pismoUtilError.getErrorCode(), pismoException.getMessage(), "", new Date().getTime()));
        return apiError;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public List<ApiErrorItem> getArguments() {
        return arguments;
    }

    public void setArguments(List<ApiErrorItem> arguments) {
        this.arguments = arguments;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

