package com.solaria.persistence.DTO.Response;

import com.solaria.persistence.Domain.enums.TechnicalAffiliationType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TechnicianAffiliationResponseDTO {

    private UUID id;
    private UUID companyId;
    private UUID technicianId;
    private TechnicalAffiliationType affiliationType;
    private Boolean active;

}
