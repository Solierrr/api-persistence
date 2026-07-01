package com.solaria.persistence.Entity;

import com.solaria.persistence.Entity.enums.BillingStatus;
import com.solaria.persistence.Entity.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "charge")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Charge {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_subscription", nullable = false)
    private Subscription subscription;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BillingStatus status = BillingStatus.PENDING;

    @Column(name = "payment_date")
    private OffsetDateTime paymentDate;
}
