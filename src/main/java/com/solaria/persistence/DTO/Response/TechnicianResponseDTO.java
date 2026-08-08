package com.solaria.persistence.DTO.Response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TechnicianResponseDTO {

    private UUID id;
    private String crea;
    private PersonResponseDTO person;

}
