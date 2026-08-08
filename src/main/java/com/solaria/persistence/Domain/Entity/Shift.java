package com.solaria.persistence.Domain.Entity;

import com.solaria.persistence.Domain.enums.DayWeek;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "shift")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_technician", nullable = false)
    private Technician technician;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_week", nullable = false)
    private DayWeek dayWeek;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;
}
