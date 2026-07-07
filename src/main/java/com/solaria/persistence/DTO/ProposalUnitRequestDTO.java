package com.solaria.persistence.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ProposalUnitRequestDTO {

    @NotNull(message = "ID do Item da Proposta é obrigatório")
    private UUID proposalItemId;

    @NotNull(message = "ID da Unidade Local é obrigatório")
    private UUID localUnitId;

    @NotNull(message = "Quantidade é obrigatória")
    private Integer quantity;

    private String note;

}
