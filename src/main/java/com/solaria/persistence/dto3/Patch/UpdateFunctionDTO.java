package com.solaria.persistence.dto3.Patch;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateFunctionDTO {

    @NotBlank(message = "Função do colaborador é obrigatória")
    private String function;

}
