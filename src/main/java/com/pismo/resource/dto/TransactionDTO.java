package com.pismo.resource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransactionDTO(
        @JsonProperty("account_id")
        @NotNull(message = "Campo account_id é obrigatório")
        Integer accountId,

        @JsonProperty("Operation_type_id")
        @NotNull(message = "Campo Operation_type_id é obrigatório")
        Integer operationId,

        BigDecimal amount) {
}
