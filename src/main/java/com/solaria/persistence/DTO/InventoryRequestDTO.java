package com.solaria.persistence.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class InventoryRequestDTO {

    @NotNull(message = "ID do Fornecedor é obrigatório")
    private UUID supplierId;

    @NotNull(message = "ID do Modelo é obrigatório")
    private UUID modelId;

    @NotNull(message = "Quantidade é obrigatória")
    private Integer quantity;

}
