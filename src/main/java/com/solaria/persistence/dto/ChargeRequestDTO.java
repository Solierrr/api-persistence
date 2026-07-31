package com.solaria.persistence.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

import com.solaria.persistence.domain.enums.PaymentMethod;

@Getter
@Setter
public class ChargeRequestDTO {

    @NotNull(message = "ID da Assinatura é obrigatório")
    private UUID subscriptionId;

    @NotNull(message = "Valor do pagamento é obrigatório")
    private BigDecimal amount;

    @NotNull(message = "Método de pagamento é obrigatório")
    private PaymentMethod paymentMethod;

}
