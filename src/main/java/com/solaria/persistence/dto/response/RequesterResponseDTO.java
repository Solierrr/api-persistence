package com.solaria.persistence.dto3.Response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RequesterResponseDTO {

    private UUID id;
    private CompanyResponseDTO company;
    private String businessType;

}
