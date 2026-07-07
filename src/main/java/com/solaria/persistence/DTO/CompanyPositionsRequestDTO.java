package com.solaria.persistence.DTO;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompanyPositionsRequestDTO {

    @NotNull(message = "ID da Empresa é obrigatório")
    private UUID companyId;

    @NotNull(message = "ID do Perfil é obrigatório")
    private UUID positionId;

}
