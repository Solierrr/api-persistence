package com.solaria.persistence.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TechnicalCompanyResponseDTO {

    private UUID id;
    private CompanyResponseDTO company;

}
