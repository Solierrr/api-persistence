package com.solaria.persistence.DTO.Patch;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class UpdateLocalUnitAddressIdDTO {

    @NotNull(message = "ID do Endereço é obrigatório")
    private UUID addressId;

}
