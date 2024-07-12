package com.pismo.model;

import com.pismo.resource.exception.BusinessError;
import com.pismo.resource.exception.PismoException;
import lombok.Getter;

import java.util.stream.Stream;

public enum OperationType {

    COMPRA_A_VISTA("COMPRA A VISTA", -1),
    COMPRA_PARCELADA("COMPRA PARCELADA", -1),
    SAQUE("SAQUE", -1),
    PAGAMENTO("PAGAMENTO", 1);

    private final String name;

    @Getter
    private final int sign;

    OperationType(String name, int sign) {
        this.name = name;
        this.sign = sign;
    }

    public static OperationType getOperationType(String name) throws PismoException {
        return Stream.of(OperationType.values())
                .filter(x -> x.name().equals(name))
                .findFirst().orElseThrow(()->new PismoException(BusinessError.OPERACAO_NAO_ENCONTRADA));
    }
}
