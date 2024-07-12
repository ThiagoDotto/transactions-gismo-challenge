package com.pismo.service;

import com.pismo.model.Operation;
import com.pismo.model.OperationType;
import com.pismo.resource.exception.PismoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculateSignValueServiceTest {

    Operation operation;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        operation = new Operation();
    }

    @Test
    void shouldBePositiveSignForPayment() throws PismoException {

        operation.setOperationType(OperationType.PAGAMENTO.name());
        BigDecimal result = CalculateSignValueService.exec(operation, (new BigDecimal("100.00")));

        assertEquals(new BigDecimal("100.00"), result);
    }


    @Test
    void shouldBeNegativeSignForAnyOperationDiferentOfPayment() throws PismoException {

        operation.setOperationType(OperationType.COMPRA_A_VISTA.name());
        BigDecimal result = CalculateSignValueService.exec(operation, new BigDecimal("100.00"));

        assertEquals(new BigDecimal("-100.00"), result);
    }
}