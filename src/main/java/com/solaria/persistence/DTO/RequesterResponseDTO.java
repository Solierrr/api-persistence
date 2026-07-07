package com.solaria.persistence.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RequesterResponseDTO {

    private UUID id;
    private CompanyResponseDTO company;

}
