package com.solaria.persistence.DTO;

import jakarta.validation.constraints.NotNull;
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
    private BigDecimal unitPrice;

    @NotNull(message = "Disponibilidade é obrigatória")
    private Integer availability;

    private OffsetDateTime expirationDate;

}
