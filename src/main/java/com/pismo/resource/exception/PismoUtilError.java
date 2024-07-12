package com.pismo.resource.exception;

import javax.ws.rs.core.Response;

public interface PismoUtilError {
    String getErrorCode();

    String getErrorMessage();

    default Response.Status getStatusCode() {
        return Response.Status.BAD_REQUEST;
    }
}