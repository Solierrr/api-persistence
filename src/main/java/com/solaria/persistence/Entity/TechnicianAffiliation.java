package com.solaria.persistence.Entity;

import com.solaria.persistence.Entity.enums.TechnicalAffiliationType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

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
