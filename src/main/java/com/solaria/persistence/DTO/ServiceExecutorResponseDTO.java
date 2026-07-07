package com.solaria.persistence.DTO;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ServiceExecutorResponseDTO {

    private UUID id;
    private UUID serviceId;
    private TechnicianAffiliationResponseDTO technicianAffiliation;
    private String position;

}
