package com.solaria.persistence.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Setter
@Getter
public class OfferRequestDTO {

    @NotNull(message = "ID do Fornecedor é obrigatório")
    private UUID supplierId;

    @NotNull(message = "ID do Modelo é obrigatório")
    private UUID modelId;

    @NotNull(message = "Preço unitário é obrigatório")
    @Positive(message = "Preço unitário deve ser maior que zero")
    private BigDecimal unitPrice;

    @NotNull(message = "Disponibilidade é obrigatória")
    @PositiveOrZero(message = "Disponibilidade não pode ser negativa")
    private Integer availability;

    private OffsetDateTime expirationDate;

}
