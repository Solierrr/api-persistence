package com.solaria.persistence.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class ProposalItemResponseDTO {

    private UUID id;
    private UUID proposalId;
    private OfferResponseDTO offer;
    private Integer quantity;
    private BigDecimal negotiatedPrice;
    private BigDecimal discount;

}
