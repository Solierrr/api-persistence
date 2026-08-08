package com.solaria.persistence.DTO.Response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ContactResponseDTO {

    private UUID id;
    private String email;
    private String phone;

}
