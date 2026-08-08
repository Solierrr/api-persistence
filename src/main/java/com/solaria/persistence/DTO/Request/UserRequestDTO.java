package com.solaria.persistence.DTO.Request;

import com.solaria.persistence.Util.RegexValidator;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserRequestDTO {

    @NotNull(message = "ID de autenticação é obrigatório")
    private UUID authId;

    @Pattern(regexp = RegexValidator.URL_REGEX, message = "Avatar inválido")
    private String avatar;

}
