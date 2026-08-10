package com.solaria.persistence.dto3.Response;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfessionalRegistrationResponseDTO {

    private UUID id;
    private UUID technicianId;
    private UUID professionId;
    private String council;
    private String number;
    private LocalDateTime expirationDate;

}
