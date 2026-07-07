package com.solaria.persistence.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
public class ServiceContractRequestDTO {

    @NotNull(message = "ID do Serviço é obrigatório")
    private UUID serviceId;

    private String warranty;

    private LocalDate deliveryDeadline;

    @NotNull(message = "Opção de seguro é obrigatório")
    private Boolean insurance;

}
