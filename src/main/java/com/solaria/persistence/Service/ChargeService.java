package com.solaria.persistence.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tools.jackson.databind.ObjectMapper;
import com.solaria.persistence.DTO.Request.ChargeRequestDTO;
import com.solaria.persistence.DTO.Response.ChargeResponseDTO;
import com.solaria.persistence.Domain.Entity.Charge;
import com.solaria.persistence.Domain.Entity.Subscription;
import com.solaria.persistence.Domain.enums.BillingStatus;
import com.solaria.persistence.Domain.enums.PaymentMethod;
import com.solaria.persistence.Exception.BusinessRuleException;
import com.solaria.persistence.Exception.InvalidFieldException;
import com.solaria.persistence.Exception.ResourceNotFoundException;
import com.solaria.persistence.Repository.ChargeRepository;
import com.solaria.persistence.Repository.SubscriptionRepository;

@Service
public class ChargeService {

    private static final BigDecimal ZERO = BigDecimal.ZERO;

    private final ChargeRepository chargeRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final ObjectMapper objectMapper;

    public ChargeService(ChargeRepository chargeRepository,
                         SubscriptionRepository subscriptionRepository,
                         ObjectMapper objectMapper) {
        this.chargeRepository = chargeRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public ChargeResponseDTO save(ChargeRequestDTO dto) {
        if (dto.getAmount() == null || dto.getAmount().compareTo(ZERO) <= 0) {
            throw new InvalidFieldException("Valor do pagamento inválido: " + dto.getAmount());
        }

        Subscription subscription = subscriptionRepository.findById(dto.getSubscriptionId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Assinatura não encontrada com ID: " + dto.getSubscriptionId()));

        Charge charge = new Charge();
        charge.setSubscription(subscription);
        charge.setAmount(dto.getAmount());
        charge.setPaymentMethod(dto.getPaymentMethod());
        charge.setStatus(BillingStatus.PENDING);
        charge.setDueDate(dto.getDueDate());
        charge.setPaymentDate(null);

        return toResponse(chargeRepository.save(charge));
    }

    @Transactional
    public ChargeResponseDTO pay(UUID id) {
        Charge charge = chargeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cobrança não encontrada com ID: " + id));

        if (charge.getStatus() != BillingStatus.PENDING) {
            throw new BusinessRuleException(
                    "Transição de status inválida: " + charge.getStatus() + " → " + BillingStatus.PAID);
        }

        charge.setStatus(BillingStatus.PAID);
        charge.setPaymentDate(OffsetDateTime.now());

        return toResponse(chargeRepository.save(charge));
    }

    @Transactional
    public ChargeResponseDTO cancel(UUID id) {
        Charge charge = chargeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cobrança não encontrada com ID: " + id));

        if (charge.getStatus() != BillingStatus.PENDING) {
            throw new BusinessRuleException(
                    "Transição de status inválida: " + charge.getStatus() + " → " + BillingStatus.CANCELED);
        }

        charge.setStatus(BillingStatus.CANCELED);

        return toResponse(chargeRepository.save(charge));
    }

    @Transactional
    public ChargeResponseDTO refund(UUID id) {
        Charge charge = chargeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cobrança não encontrada com ID: " + id));

        if (charge.getStatus() != BillingStatus.PAID) {
            throw new BusinessRuleException(
                    "Transição de status inválida: " + charge.getStatus() + " → " + BillingStatus.REFUNDED);
        }

        charge.setStatus(BillingStatus.REFUNDED);

        return toResponse(chargeRepository.save(charge));
    }

    @Transactional
    public void generateFromSubscription(UUID subscriptionId, LocalDate dueDate, PaymentMethod paymentMethod) {
        if (!subscriptionRepository.existsById(subscriptionId)) {
            throw new ResourceNotFoundException("Assinatura não encontrada com ID: " + subscriptionId);
        }
        subscriptionRepository.callGenerateSubscriptionCharge(subscriptionId, dueDate, paymentMethod.name());
    }

    @Transactional(readOnly = true)
    public ChargeResponseDTO findById(UUID id) {
        Charge charge = chargeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cobrança não encontrada com ID: " + id));
        return toResponse(charge);
    }

    @Transactional(readOnly = true)
    public ChargeResponseDTO findById(UUID id, UUID companyId) {
        Charge charge = chargeRepository.findByIdAndSubscription_Supplier_Company_Id(id, companyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cobrança não encontrada com ID: " + id));
        return toResponse(charge);
    }

    @Transactional(readOnly = true)
    public List<ChargeResponseDTO> findBySubscription(UUID subscriptionId) {
        return chargeRepository.findBySubscriptionId(subscriptionId).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ChargeResponseDTO> findAllByCompany(UUID companyId) {
        return chargeRepository.findBySubscription_Supplier_Company_Id(companyId).stream()
                .map(this::toResponse)
                .toList();
    }

    private ChargeResponseDTO toResponse(Charge charge) {
        ChargeResponseDTO response = objectMapper.convertValue(charge, ChargeResponseDTO.class);
        response.setSubscriptionId(charge.getSubscription().getId());
        return response;
    }
}
