package com.pismo.service;

import com.pismo.model.Operation;
import com.pismo.model.OperationType;
import com.pismo.resource.exception.PismoException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculateSignValueService {

    public static BigDecimal exec(Operation operation, BigDecimal amount) throws PismoException {
        OperationType operationType = OperationType.getOperationType(operation.getOperationType());
        return amount.multiply(new BigDecimal(operationType.getSign())).setScale(2, RoundingMode.FLOOR);
    }
}
