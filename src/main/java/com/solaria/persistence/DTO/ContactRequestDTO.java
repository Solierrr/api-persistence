package com.solaria.persistence.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContactRequestDTO {

    @Email
    @Size(max = 100)
    private String workEmail;

    @Email
    @Size(max = 100)
    private String personalEmail;

    @Size(max = 12)
    private String workPhone;

    @Size(max = 12)
    private String personalPhone;

}
