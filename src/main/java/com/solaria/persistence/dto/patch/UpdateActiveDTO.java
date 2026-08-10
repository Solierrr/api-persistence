package com.solaria.persistence.dto3.Patch;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateActiveDTO {

    @NotNull(message = "active é obrigatório")
    private Boolean active;

}
