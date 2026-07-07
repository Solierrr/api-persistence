package com.solaria.persistence.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnergyBillResponseDTO {

    private UUID id;
    private UUID localUnitId;
    private BigDecimal consumption;
    private BigDecimal price;

}
