package com.solaria.persistence.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

import com.solaria.persistence.domain.enums.TechnicalAffiliationType;

@Entity
@Table(name = "technician_affiliation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TechnicianAffiliation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_company", nullable = false)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_technician", nullable = false)
    private Technician technician;

    @Enumerated(EnumType.STRING)
    @Column(name = "affiliation_type", nullable = false)
    private TechnicalAffiliationType affiliationType;
}
