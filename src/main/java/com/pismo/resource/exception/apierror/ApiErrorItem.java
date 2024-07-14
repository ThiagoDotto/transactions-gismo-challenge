package com.pismo.resource.exception.apierror;

import java.util.Date;

public class ApiErrorItem {

    private String code;
    private String message;
    private String owner;
    private Long date;

    public ApiErrorItem(String code, String message, String owner, Long date) {
        this.code = code;
        this.message = message;
        this.owner = owner;
        this.date = new Date().getTime();
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getOwner() {
        return owner;
    }

    public Long getDate() {
        return date;
    }
}
