package com.solaria.persistence.DTO;

import com.solaria.persistence.Domain.enums.SupplierStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SupplierResponseDTO {

    private UUID id;
    private CompanyResponseDTO company;
    private SupplierStatus status;

}
