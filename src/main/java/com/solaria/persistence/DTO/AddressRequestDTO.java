package com.solaria.persistence.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressRequestDTO {

    @NotBlank(message = "Estado é obrigatório")
    @Size(max = 2)
    private String state;

    @NotBlank(message = "Cidade é obrigatória")
    private String city;

    private String neighborhood;

    @NotBlank(message = "CEP é obrigatório")
    @Size(max = 8)
    private String zipCode;

    @NotBlank(message = "Rua é obrigatória")
    private String street;

    @Size(max = 10)
    private String number;

}
