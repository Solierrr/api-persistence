package com.solaria.persistence.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "company_roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_company", nullable = false)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_role", nullable = false)
    private Role role;
}
