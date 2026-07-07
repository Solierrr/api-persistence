package com.solaria.persistence.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class CompanyRequestDTO {

    @NotNull(message = "ID do Endereço é obrigatório")
    private UUID addressId;

    @NotNull(message = "ID do Contato Empresarial é obrigatório")
    private UUID businessContactId;

    @NotBlank(message = "CNPJ é obrigatório")
    @Size(max = 14)
    private String cnpj;

    @NotBlank(message = "Nome Fantasia da empresa é obrigatório")
    @Size(max = 120)
    private String tradeName;

}
