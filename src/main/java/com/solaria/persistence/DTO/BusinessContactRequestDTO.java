package com.solaria.persistence.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessContactRequestDTO {

    @NotBlank(message = "Email da empresa é obrigatório")
    @Email
    @Size(max = 100)
    private String companyEmail;

    @Size(max = 12)
    private String phone;

    private String website;

}
