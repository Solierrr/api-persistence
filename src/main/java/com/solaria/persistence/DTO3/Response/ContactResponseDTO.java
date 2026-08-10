package com.solaria.persistence.dto.response;

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
