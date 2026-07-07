package com.solaria.persistence.DTO;

import com.solaria.persistence.Domain.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

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
