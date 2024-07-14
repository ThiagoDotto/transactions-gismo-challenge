package com.pismo.resource.exception;

import javax.ws.rs.core.Response;

public class BusinessError implements PismoUtilError{

    public static final BusinessError NAO_FOI_POSSIVEL_SALVAR_A_CONTA = new BusinessError("001", "Já existe uma conta para esse numero de documento.");
    public static final BusinessError CONTA_NAO_ENCONTRADA = new BusinessError("001", "Conta não encontrada.");
    public static final BusinessError OPERACAO_NAO_ENCONTRADA = new BusinessError("001", "Operação não encontrada.");

    private String code;
    private String message;

    public BusinessError(String code, String message) {
        this.code = code;
        this.message = message;
    }
    @Override
    public String getErrorCode() {
        return this.code;
    }

    @Override
    public String getErrorMessage() {
        return this.message;
    }
}
