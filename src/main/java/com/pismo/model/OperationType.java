package com.pismo.model;

public enum OperationType {

    COMPRA_A_VISTA("COMPRA A VISTA"),
    COMPRA_PARCELADA("COMPRA PARCELADA"),
    SAQUE("SAQUE"),
    PAGAMENTO("PAGAMENTO");

    private final String name;

    OperationType(String name) {
        this.name = name;
    }
}
