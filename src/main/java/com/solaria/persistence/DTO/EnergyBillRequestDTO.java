package com.solaria.persistence.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class EnergyBillRequestDTO {

    @NotNull(message = "ID da Unidade Local é obrigatório")
    private UUID localUnitId;

    @NotNull(message = "Consumo é obrigatório")
    private BigDecimal consumption;

    @NotNull(message = "Preço é obrigatório")
    private BigDecimal price;

}
