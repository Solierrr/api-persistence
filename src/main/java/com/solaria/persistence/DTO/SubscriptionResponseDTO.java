package com.solaria.persistence.DTO;

import com.solaria.persistence.Domain.enums.SubscriptionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

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
