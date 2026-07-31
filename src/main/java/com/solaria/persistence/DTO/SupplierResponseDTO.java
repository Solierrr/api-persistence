package com.solaria.persistence.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import com.solaria.persistence.domain.enums.SupplierStatus;

@Getter
@Setter
public class SupplierResponseDTO {

    private UUID id;
    private CompanyResponseDTO company;
    private SupplierStatus status;

}
