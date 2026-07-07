package com.solaria.persistence.Domain.Entity;

import com.solaria.persistence.Domain.enums.ModelStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "model")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "power_wp", nullable = false)
    private BigDecimal powerWp;

    @Column(name = "efficiency", nullable = false)
    private BigDecimal efficiency;

    @Column(name = "dimension", nullable = false)
    private BigDecimal dimension;

    @Column(name = "weight", nullable = false)
    private BigDecimal weight;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ModelStatus status = ModelStatus.UNDER_ANALYSIS;
}
