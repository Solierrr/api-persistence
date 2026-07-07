package com.solaria.persistence.DTO;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PositionPermissionResponseDTO {

    private UUID id;
    private UUID positionId;
    private PermissionResponseDTO permission;

}
