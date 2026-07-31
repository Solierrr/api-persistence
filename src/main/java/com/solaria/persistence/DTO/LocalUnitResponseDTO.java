package com.solaria.persistence.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import com.solaria.persistence.domain.enums.LocationType;

@Getter
@Setter
public class LocalUnitResponseDTO {

    private UUID id;
    private UUID requesterId;
    private AddressResponseDTO address;
    private String complement;
    private LocationType locationType;

}
