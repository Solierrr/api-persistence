package com.solaria.persistence.DTO;

import com.solaria.persistence.Domain.enums.ServiceStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
public class TechnicalServiceResponseDTO {

    private UUID id;
    private UUID requesterId;
    private String purpose;
    private ServiceStatus status;
    private OffsetDateTime createdAt;

}
