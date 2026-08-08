package com.solaria.persistence.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tools.jackson.databind.ObjectMapper;
import com.solaria.persistence.DTO.Request.CompanyPositionsRequestDTO;
import com.solaria.persistence.DTO.Response.CompanyPositionsResponseDTO;
import com.solaria.persistence.DTO.Response.PositionResponseDTO;
import com.solaria.persistence.Domain.Entity.Company;
import com.solaria.persistence.Domain.Entity.CompanyPositions;
import com.solaria.persistence.Domain.Entity.Position;
import com.solaria.persistence.Exception.DuplicateResourceException;
import com.solaria.persistence.Exception.ResourceInUseException;
import com.solaria.persistence.Exception.ResourceNotFoundException;
import com.solaria.persistence.Repository.CompanyPositionsRepository;
import com.solaria.persistence.Repository.CompanyRepository;
import com.solaria.persistence.Repository.PositionRepository;
import com.solaria.persistence.Repository.UserCompanyRepository;

@Service
public class CompanyPositionsService {

    private final CompanyPositionsRepository companyPositionsRepository;
    private final CompanyRepository companyRepository;
    private final PositionRepository positionRepository;
    private final UserCompanyRepository userCompanyRepository;
    private final ObjectMapper objectMapper;

    public CompanyPositionsService(CompanyPositionsRepository companyPositionsRepository,
                                   CompanyRepository companyRepository,
                                   PositionRepository positionRepository,
                                   UserCompanyRepository userCompanyRepository,
                                   ObjectMapper objectMapper) {
        this.companyPositionsRepository = companyPositionsRepository;
        this.companyRepository = companyRepository;
        this.positionRepository = positionRepository;
        this.userCompanyRepository = userCompanyRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public CompanyPositionsResponseDTO save(CompanyPositionsRequestDTO dto) {
        Company company = companyRepository.findById(dto.getCompanyId()).orElseThrow(
                () -> new ResourceNotFoundException("Empresa não encontrada com ID: " + dto.getCompanyId()));
        Position position = positionRepository.findById(dto.getPositionId()).orElseThrow(
                () -> new ResourceNotFoundException("Cargo não encontrado com ID: " + dto.getPositionId()));

        if (companyPositionsRepository.existsByCompanyIdAndPositionId(dto.getCompanyId(), dto.getPositionId())) {
            throw new DuplicateResourceException(
                    "Cargo já disponibilizado para a empresa: " + dto.getCompanyId());
        }

        CompanyPositions companyPositions = new CompanyPositions();
        companyPositions.setCompany(company);
        companyPositions.setPosition(position);

        return toResponse(companyPositionsRepository.save(companyPositions));
    }

    @Transactional
    public void deleteById(UUID id) {
        CompanyPositions companyPositions = companyPositionsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Cargo da empresa com id:" + id + " não encontrado(a) para exclusão"));

        UUID companyId = companyPositions.getCompany().getId();
        UUID positionId = companyPositions.getPosition().getId();

        if (userCompanyRepository.existsByCompanyIdAndPositionId(companyId, positionId)) {
            throw new ResourceInUseException("Cargo não pode ser excluído(a): possui usuário(s) vinculado(s) na empresa");
        }

        companyPositionsRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public CompanyPositionsResponseDTO findById(UUID id) {
        CompanyPositions companyPositions = companyPositionsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cargo da empresa não encontrado(a) com ID: " + id));
        return toResponse(companyPositions);
    }

    @Transactional(readOnly = true)
    public CompanyPositionsResponseDTO findById(UUID id, UUID companyId) {
        CompanyPositions companyPositions = companyPositionsRepository.findByIdAndCompanyId(id, companyId).orElseThrow(
                () -> new ResourceNotFoundException("Cargo da empresa não encontrado(a) com ID: " + id));
        return toResponse(companyPositions);
    }

    @Transactional(readOnly = true)
    public List<CompanyPositionsResponseDTO> findAllByCompany(UUID companyId) {
        return companyPositionsRepository.findByCompanyId(companyId).stream().map(this::toResponse).toList();
    }

    private CompanyPositionsResponseDTO toResponse(CompanyPositions entity) {
        CompanyPositionsResponseDTO response = objectMapper.convertValue(entity, CompanyPositionsResponseDTO.class);
        response.setCompanyId(entity.getCompany().getId());
        response.setPosition(objectMapper.convertValue(entity.getPosition(), PositionResponseDTO.class));
        return response;
    }
}
