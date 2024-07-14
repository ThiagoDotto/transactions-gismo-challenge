package com.pismo.resource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AccountDTO(

        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, minimum = "1")
        @JsonProperty("document_number")
        @Min(message = "O Valor minimo para o ducumento é 1", value = 01)
        @NotNull(message = "Campo document_number obrigatório")
        Long documentNumber) {
}
