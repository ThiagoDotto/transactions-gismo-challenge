package com.pismo.resource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record TransactionDTO(
        @JsonProperty("account_id")
        Integer accountId,

        @JsonProperty("Operation_type_id")
        int operationId,

        BigDecimal amount) {
}
