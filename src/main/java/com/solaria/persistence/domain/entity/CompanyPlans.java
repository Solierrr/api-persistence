package com.solaria.persistence.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

import com.solaria.persistence.domain.enums.PlanCycle;

@Entity
@Table(name = "company_plans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyPlans {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    @Column(name = "cycle", nullable = false)
    private PlanCycle cycle;
}
