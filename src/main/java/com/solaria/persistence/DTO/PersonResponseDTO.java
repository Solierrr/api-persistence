package com.solaria.persistence.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
public class PersonResponseDTO {

    private UUID id;
    private String name;
    private String cpf;
    private LocalDate birthDate;
    private UUID userId;
    private ContactResponseDTO contact;

}
