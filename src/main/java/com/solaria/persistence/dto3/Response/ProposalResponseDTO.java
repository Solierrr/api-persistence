package com.solaria.persistence.dto3.Response;

import com.solaria.persistence.domain.enums.ProposalStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Setter
@Getter
public class ProposalResponseDTO {

    private UUID id;
    private UUID requesterId;
    private ProposalStatus status;
    private String notes;
    private BigDecimal totalAmount;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

}
