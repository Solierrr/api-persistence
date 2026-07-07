package com.solaria.persistence.Domain.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "contact")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "work_email", length = 100)
    private String workEmail;

    @Column(name = "personal_email", length = 100)
    private String personalEmail;

    @Column(name = "work_phone", length = 12)
    private String workPhone;

    @Column(name = "personal_phone", length = 12)
    private String personalPhone;
}
