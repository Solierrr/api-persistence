package com.solaria.persistence.Entity;

import com.solaria.persistence.Entity.enums.ServiceStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "technical_service")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TechnicalService {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_requester", nullable = false)
    private Requester requester;

    @Column(name = "purpose", nullable = false)
    private String purpose;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ServiceStatus status = ServiceStatus.OPEN;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
}
