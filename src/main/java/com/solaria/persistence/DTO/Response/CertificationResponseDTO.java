package com.solaria.persistence.DTO.Response;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CertificationResponseDTO {

    private UUID id;
    private String name;
    private String issuer;
    private LocalDateTime validity;
    private String description;

}
