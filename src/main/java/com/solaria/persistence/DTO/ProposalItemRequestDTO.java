package com.solaria.persistence.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class ProposalItemRequestDTO {

    @NotNull(message = "ID da Proposta é obrigatório")
    private UUID proposalId;

    @NotNull(message = "ID da Oferta é obrigatório")
    private UUID offerId;

    @NotNull(message = "Quantidade é obrigatória")
    private Integer quantity;

    private BigDecimal negotiatedPrice;

    private BigDecimal discount;

}
