package com.solaria.persistence.dto.response;

import com.solaria.persistence.domain.enums.TechnicalAffiliationType;
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
