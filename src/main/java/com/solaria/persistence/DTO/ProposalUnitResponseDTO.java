package com.solaria.persistence.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ProposalUnitResponseDTO {

    private UUID id;
    private UUID proposalItemId;
    private LocalUnitResponseDTO localUnit;
    private Integer quantity;
    private String note;

}
