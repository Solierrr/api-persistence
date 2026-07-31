package com.solaria.persistence.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ContactResponseDTO {

    private UUID id;
    private String workEmail;
    private String personalEmail;
    private String workPhone;
    private String personalPhone;

}
