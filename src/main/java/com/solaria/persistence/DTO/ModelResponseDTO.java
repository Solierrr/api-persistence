package com.solaria.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

import com.solaria.persistence.domain.enums.ModelStatus;

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
