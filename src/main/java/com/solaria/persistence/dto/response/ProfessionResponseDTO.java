package com.solaria.persistence.dto3.Response;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfessionResponseDTO {

    private UUID id;
    private String name;
    private Boolean requiresRegistration;

}
