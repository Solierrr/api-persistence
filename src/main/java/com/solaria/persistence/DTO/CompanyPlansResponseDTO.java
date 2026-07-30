package com.solaria.persistence.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

import com.solaria.persistence.domain.enums.PlanCycle;

@Getter
@Setter
public class CompanyPlansResponseDTO {

    private UUID id;
    private String name;
    private BigDecimal value;
    private PlanCycle cycle;

}
