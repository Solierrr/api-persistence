package com.solaria.persistence.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import com.solaria.persistence.domain.enums.TechnicalAffiliationType;

@Getter
@Setter
public class TechnicianAffiliationResponseDTO {

    private UUID id;
    private UUID companyId;
    private UUID technicianId;
    private TechnicalAffiliationType affiliationType;

}
