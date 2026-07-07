package com.solaria.persistence.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SpecializationRequestDTO {

    @NotBlank(message = "Tipo de especialização é obrigatório")
    private String type;

}
