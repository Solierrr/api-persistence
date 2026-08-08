package com.solaria.persistence.DTO.Patch;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class UpdateBusinessContactIdDTO {

    @NotNull(message = "ID do Contato Empresarial é obrigatório")
    private UUID businessContactId;

}
