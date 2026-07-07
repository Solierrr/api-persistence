package com.solaria.persistence.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CompanyResponseDTO {

    private UUID id;
    private String cnpj;
    private String tradeName;
    private AddressResponseDTO address;
    private BusinessContactResponseDTO businessContact;

}
