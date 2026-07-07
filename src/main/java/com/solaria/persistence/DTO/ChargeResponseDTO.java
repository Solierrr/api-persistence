package com.solaria.persistence.DTO;

import com.solaria.persistence.Domain.enums.BillingStatus;
import com.solaria.persistence.Domain.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

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
