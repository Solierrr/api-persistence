package com.solaria.persistence.DTO.Response;

import com.solaria.persistence.Domain.enums.CompanyStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CompanyResponseDTO {

    private UUID id;
    private CompanyStatus status;
    private String cnpj;
    private String tradeName;
    private String corporateName;
    private AddressResponseDTO address;
    private BusinessContactResponseDTO businessContact;

}
