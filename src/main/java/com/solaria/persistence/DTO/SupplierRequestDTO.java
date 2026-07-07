package com.solaria.persistence.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class SupplierRequestDTO {

    @NotNull(message = "ID da Empresa é obrigatório")
    private UUID companyId;

}
