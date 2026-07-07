package com.solaria.persistence.DTO;

import com.solaria.persistence.Domain.enums.LocationType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class LocalUnitResponseDTO {

    private UUID id;
    private UUID requesterId;
    private AddressResponseDTO address;
    private String complement;
    private LocationType locationType;

}
