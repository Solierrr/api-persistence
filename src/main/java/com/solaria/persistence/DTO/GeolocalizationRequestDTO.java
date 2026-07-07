
package com.solaria.persistence.DTO;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeolocalizationRequestDTO {

    private UUID addressId;

    private BigDecimal latitude;

    private BigDecimal longitude;

}
