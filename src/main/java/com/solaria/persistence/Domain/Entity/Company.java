package com.solaria.persistence.Domain.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_address", nullable = false)
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_business_contact", nullable = false)
    private BusinessContact businessContact;

    @Column(name = "cnpj", length = 14, nullable = false)
    private String cnpj;

    @Column(name = "trade_name", length = 120, nullable = false)
    private String tradeName;
}
