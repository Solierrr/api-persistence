package com.solaria.persistence.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionRequestDTO {

    @NotBlank(message = "Nome da permissão é obrigatório")
    @Size(max = 100)
    private String permissionName;

}
