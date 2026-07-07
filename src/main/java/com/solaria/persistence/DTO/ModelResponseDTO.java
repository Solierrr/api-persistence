package com.solaria.persistence.DTO;

import com.solaria.persistence.Domain.enums.ModelStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelResponseDTO {

    private UUID id;
    private String brand;
    private String model;
    private BigDecimal powerWp;
    private BigDecimal efficiency;
    private BigDecimal dimension;
    private BigDecimal weight;
    private ModelStatus status;

}
