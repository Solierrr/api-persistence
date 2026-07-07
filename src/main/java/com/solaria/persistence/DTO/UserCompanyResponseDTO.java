package com.solaria.persistence.DTO;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserCompanyResponseDTO {

    private UUID id;
    private UUID companyId;
    private UUID userId;
    private PositionResponseDTO position;

}
