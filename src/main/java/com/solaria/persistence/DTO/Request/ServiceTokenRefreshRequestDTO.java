package com.solaria.persistence.dto.request;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ServiceTokenRefreshRequestDTO {


    @NotBlank(message = "refreshToken é obrigatório")
    private String refreshToken;
}
