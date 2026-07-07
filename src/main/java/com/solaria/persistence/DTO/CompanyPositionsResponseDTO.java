package com.solaria.persistence.DTO;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyPositionsResponseDTO {

    private UUID id;
    private UUID companyId;
    private PositionResponseDTO position;

}
