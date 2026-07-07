package com.solaria.persistence.DTO;

import com.solaria.persistence.Domain.enums.PlanCycle;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class CompanyPlansResponseDTO {

    private UUID id;
    private String name;
    private BigDecimal value;
    private PlanCycle cycle;

}
