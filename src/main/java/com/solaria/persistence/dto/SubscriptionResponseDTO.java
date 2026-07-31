package com.solaria.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.solaria.persistence.domain.enums.SubscriptionStatus;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionResponseDTO {

    private UUID id;
    private UUID supplierId;
    private CompanyPlansResponseDTO plan;
    private SubscriptionStatus status;
    private Boolean autoRenewal;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;

}
