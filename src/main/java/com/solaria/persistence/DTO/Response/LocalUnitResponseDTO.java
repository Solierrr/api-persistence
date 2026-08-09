package com.solaria.persistence.dto.response;

import com.solaria.persistence.domain.enums.LocationType;
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
