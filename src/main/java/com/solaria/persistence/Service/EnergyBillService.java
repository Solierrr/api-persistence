package com.solaria.persistence.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tools.jackson.databind.ObjectMapper;
import com.solaria.persistence.dto.request.EnergyBillRequestDTO;
import com.solaria.persistence.dto.response.EnergyBillResponseDTO;
import com.solaria.persistence.domain.entity.EnergyBill;
import com.solaria.persistence.domain.entity.LocalUnit;
import com.solaria.persistence.exception.InvalidFieldException;
import com.solaria.persistence.exception.ResourceNotFoundException;
import com.solaria.persistence.repository.EnergyBillRepository;
import com.solaria.persistence.repository.LocalUnitRepository;


@Service
public class EnergyBillService {

    private static final BigDecimal ZERO = BigDecimal.ZERO;

    private final EnergyBillRepository energyBillRepository;
    private final LocalUnitRepository localUnitRepository;
    private final ObjectMapper objectMapper;

    public EnergyBillService(EnergyBillRepository energyBillRepository,
                             LocalUnitRepository localUnitRepository,
                             ObjectMapper objectMapper) {
        this.energyBillRepository = energyBillRepository;
        this.localUnitRepository = localUnitRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public EnergyBillResponseDTO save(EnergyBillRequestDTO dto) {
        if (dto.getConsumption() == null || dto.getConsumption().compareTo(ZERO) <= 0) {
            throw new InvalidFieldException("Consumo inválido: " + dto.getConsumption());
        }
        if (dto.getPrice() == null || dto.getPrice().compareTo(ZERO) < 0) {
            throw new InvalidFieldException("Preço inválido: " + dto.getPrice());
        }

        LocalUnit localUnit = localUnitRepository.findById(dto.getLocalUnitId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Unidade Local não encontrada com ID: " + dto.getLocalUnitId()));

        EnergyBill energyBill = new EnergyBill();
        energyBill.setLocalUnit(localUnit);
        energyBill.setConsumption(dto.getConsumption());
        energyBill.setPrice(dto.getPrice());

        return toResponse(energyBillRepository.save(energyBill));
    }

    @Transactional(readOnly = true)
    public EnergyBillResponseDTO findById(UUID id) {
        EnergyBill energyBill = energyBillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Conta de Energia não encontrada com ID: " + id));
        return toResponse(energyBill);
    }

    @Transactional(readOnly = true)
    public EnergyBillResponseDTO findById(UUID id, UUID companyId) {
        EnergyBill energyBill = energyBillRepository
                .findByIdAndLocalUnit_Requester_Company_Id(id, companyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Conta de Energia não encontrada com ID: " + id));
        return toResponse(energyBill);
    }

    @Transactional(readOnly = true)
    public List<EnergyBillResponseDTO> findByLocalUnit(UUID localUnitId) {
        return energyBillRepository.findByLocalUnitId(localUnitId).stream()
                .map(this::toResponse)
                .toList();
    }

    private EnergyBillResponseDTO toResponse(EnergyBill energyBill) {
        EnergyBillResponseDTO response = objectMapper.convertValue(energyBill, EnergyBillResponseDTO.class);
        response.setLocalUnitId(energyBill.getLocalUnit().getId());
        return response;
    }
}
