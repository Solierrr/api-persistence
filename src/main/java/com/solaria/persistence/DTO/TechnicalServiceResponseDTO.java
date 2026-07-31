package com.solaria.persistence.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.solaria.persistence.domain.enums.ServiceStatus;

@Getter
@Setter
public class TechnicalServiceResponseDTO {

    private UUID id;
    private UUID requesterId;
    private String purpose;
    private ServiceStatus status;
    private OffsetDateTime createdAt;

}
