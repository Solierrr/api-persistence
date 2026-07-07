package com.solaria.persistence.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TechnicalServiceRequestDTO {

    @NotNull(message = "ID do Solicitante é obrigatório")
    private UUID requesterId;

    @NotBlank(message = "Finalidade do serviço é obrigatória")
    private String purpose;

}
