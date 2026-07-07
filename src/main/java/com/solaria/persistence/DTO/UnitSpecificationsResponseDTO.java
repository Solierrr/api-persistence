package com.solaria.persistence.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
public class UnitSpecificationsResponseDTO {

    private UUID id;
    private UUID localUnitId;
    private String specifications;
    private String locationPhotos;
    private OffsetDateTime date;

}
