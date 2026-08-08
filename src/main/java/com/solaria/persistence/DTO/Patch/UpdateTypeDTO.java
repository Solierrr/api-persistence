package com.solaria.persistence.DTO.Patch;

import com.solaria.persistence.Domain.enums.TechnicalAffiliationType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateTypeDTO {

    @NotNull(message = "Tipo de afiliação é obrigatório")
    private TechnicalAffiliationType affiliationType;

}
