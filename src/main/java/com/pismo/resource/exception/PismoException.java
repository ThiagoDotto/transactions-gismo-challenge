package com.pismo.resource.exception;


public class PismoException extends Exception {

    private PismoUtilError pismoUtilError;

    public PismoException(PismoUtilError pismoUtilError) {
        super(pismoUtilError.getErrorMessage());
        this.pismoUtilError = pismoUtilError;
    }

    public PismoException(PismoUtilError pismoUtilError, Throwable e) {
        super(pismoUtilError.getErrorMessage(), e);
        this.pismoUtilError = pismoUtilError;
    }

    public PismoException(PismoUtilError pismoUtilError, Object... args) {
        super(String.format(pismoUtilError.getErrorMessage(), args));
        this.pismoUtilError = pismoUtilError;
    }

    public PismoException(PismoUtilError pismoUtilError, Throwable e, Object... args) {
        super(String.format(pismoUtilError.getErrorMessage(), args), e);
        this.pismoUtilError = pismoUtilError;
    }
    public PismoUtilError getPismoUtilError() {
        return pismoUtilError;
    }

    public void setPismoUtilError(PismoUtilError pismoUtilError) {
        this.pismoUtilError = pismoUtilError;
    }
}

