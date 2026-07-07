package com.solaria.persistence.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TechnicianSpecializationResponseDTO {

    private UUID id;
    private UUID technicianId;
    private SpecializationResponseDTO specialization;

}
