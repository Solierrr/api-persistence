package com.solaria.persistence.dto.response;

import com.solaria.persistence.domain.enums.SupplierStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SupplierResponseDTO {

    private UUID id;
    private CompanyResponseDTO company;
    private SupplierStatus status;
    private String businessType;

}
