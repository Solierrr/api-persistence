package com.solaria.persistence.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ModelRequestDTO {

    @NotBlank(message = "Marca é obrigatória")
    private String brand;

    @NotBlank(message = "Modelo é obrigatório")
    private String model;

    @NotNull(message = "Potência (Wp) é obrigatória")
    private BigDecimal powerWp;

    @NotNull(message = "Eficiência é obrigatória")
    private BigDecimal efficiency;

    @NotNull(message = "Dimensão é obrigatória")
    private BigDecimal dimension;

    @NotNull(message = "Peso é obrigatório")
    private BigDecimal weight;

}
