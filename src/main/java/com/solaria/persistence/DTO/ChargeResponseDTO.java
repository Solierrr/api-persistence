package com.solaria.persistence.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import com.solaria.persistence.domain.enums.BillingStatus;
import com.solaria.persistence.domain.enums.PaymentMethod;

@Setter
@Getter
public class ChargeResponseDTO {

    private UUID id;
    private UUID subscriptionId;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private BillingStatus status;
    private OffsetDateTime paymentDate;

}
