package com.pismo.service;

import com.pismo.model.Operation;
import com.pismo.model.OperationType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class CalculateSignValueService {

    public static BigDecimal exec(Operation operation, BigDecimal amount) {
        int sing = Objects.equals(operation.getOperationType(), OperationType.PAGAMENTO.name()) ? 1 : -1;
        return amount.multiply(new BigDecimal(sing)).setScale(2, RoundingMode.FLOOR);
    }
}
