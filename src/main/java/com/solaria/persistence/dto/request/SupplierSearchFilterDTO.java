package com.solaria.persistence.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierSearchFilterDTO {

    private String query;

    @Size(max = 2)
    private String state;

    private String city;

    private String neighborhood;

    @Size(max = 40)
    private String businessType;

    @PositiveOrZero(message = "Página não pode ser negativa")
    private Integer page;

    @Positive(message = "Tamanho da página deve ser maior que zero")
    @Max(value = 100, message = "Tamanho da página não pode ser maior que 100")
    private Integer size;
}
