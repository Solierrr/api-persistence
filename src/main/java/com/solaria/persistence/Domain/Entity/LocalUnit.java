package com.solaria.persistence.Domain.Entity;

import com.solaria.persistence.Domain.enums.LocationType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "local_unit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocalUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_requester", nullable = false)
    private Requester requester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_address", nullable = false)
    private Address address;

    @Column(name = "complement")
    private String complement;

    @Enumerated(EnumType.STRING)
    @Column(name = "location_type", nullable = false)
    private LocationType locationType;
}
