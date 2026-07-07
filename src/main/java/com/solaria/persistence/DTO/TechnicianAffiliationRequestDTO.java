package com.solaria.persistence.DTO;

import com.solaria.persistence.Domain.enums.TechnicalAffiliationType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TechnicianAffiliationRequestDTO {

    @NotNull(message = "ID da Empresa é obrigatório")
    private UUID companyId;

    @NotNull(message = "ID do Técnico é obrigatório")
    private UUID technicianId;

    @NotNull(message = "Tipo de afiliação é obrigatório")
    private TechnicalAffiliationType affiliationType;

}
