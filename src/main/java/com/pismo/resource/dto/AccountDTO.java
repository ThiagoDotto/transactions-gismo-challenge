package com.pismo.resource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public record AccountDTO(

        @Schema(requiredMode = Schema.RequiredMode.REQUIRED,minimum = "1")
        @JsonProperty("document_number")
        long documentNumber) {
}
