package com.pismo.resource.exception;

public class BusinessError implements PismoUtilError{

    public static final BusinessError NAO_FOI_POSSIVEL_SALVAR_A_CONTA = new BusinessError("001", "Não foi possível criar uma conta.");

    private String code;
    private String message;
    public BusinessError(String code, String message) {
        this.code = code;
        this.message = message;
    }
    @Override
    public String getErrorCode() {
        return "";
    }

    @Override
    public String getErrorMessage() {
        return "";
    }
}
