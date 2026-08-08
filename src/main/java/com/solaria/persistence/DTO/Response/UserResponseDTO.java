package com.solaria.persistence.DTO.Response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserResponseDTO {

    private UUID id;
    private UUID authId;
    private String avatar;
    private Boolean active;

}
