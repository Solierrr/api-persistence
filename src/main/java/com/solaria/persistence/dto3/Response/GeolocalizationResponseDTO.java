package com.solaria.persistence.dto3.Response;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeolocalizationResponseDTO {

    private UUID id;

    private UUID addressId;

    private BigDecimal latitude;

    private BigDecimal longitude;

}
