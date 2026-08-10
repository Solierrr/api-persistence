package com.solaria.persistence.dto3.Request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CertificationRequestDTO {

    @Size(max = 100)
    private String name;

    @Size(max = 100)
    private String issuer;

    private LocalDateTime validity;

    private String description;

}
