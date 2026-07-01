package com.solaria.persistence.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "service_executor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceExecutor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_service", nullable = false)
    private TechnicalService service;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_technician_affiliation", nullable = false)
    private TechnicianAffiliation technicianAffiliation;

    @Column(name = "role", nullable = false)
    private String role;
}
