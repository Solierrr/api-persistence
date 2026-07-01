package com.solaria.persistence.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "technician")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Technician {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_person", nullable = false)
    private Person person;

    @Column(name = "crea", nullable = false)
    private String crea;
}
