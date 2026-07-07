package com.solaria.persistence.DTO;

import com.solaria.persistence.Domain.enums.LocationType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class LocalUnitRequestDTO {

    @NotNull(message = "ID do Solicitante é obrigatório")
    private UUID requesterId;

    @NotNull(message = "ID do Endereço é obrigatório")
    private UUID addressId;

    private String complement;

    @NotNull(message = "Tipo de localização é obrigatório")
    private LocationType locationType;

}
