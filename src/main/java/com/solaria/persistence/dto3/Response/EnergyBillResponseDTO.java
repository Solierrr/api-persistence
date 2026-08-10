package com.solaria.persistence.dto3.Response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class EnergyBillResponseDTO {

    private UUID id;
    private UUID localUnitId;
    private BigDecimal consumption;
    private BigDecimal price;

}
