package com.solaria.persistence.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
public class OfferResponseDTO {

    private UUID id;
    private UUID supplierId;
    private ModelResponseDTO model;
    private BigDecimal unitPrice;
    private Integer availability;
    private OffsetDateTime expirationDate;

}
