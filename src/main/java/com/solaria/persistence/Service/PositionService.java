package com.solaria.persistence.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tools.jackson.databind.ObjectMapper;
import com.solaria.persistence.DTO.Request.PositionRequestDTO;
import com.solaria.persistence.DTO.Response.PositionResponseDTO;
import com.solaria.persistence.Domain.Entity.Position;
import com.solaria.persistence.Exception.ResourceInUseException;
import com.solaria.persistence.Exception.ResourceNotFoundException;
import com.solaria.persistence.Repository.CompanyPositionsRepository;
import com.solaria.persistence.Repository.PositionPermissionRepository;
import com.solaria.persistence.Repository.PositionRepository;
import com.solaria.persistence.Repository.UserCompanyRepository;


@Service
public class PositionService {

    private final PositionRepository positionRepository;
    private final PositionPermissionRepository positionPermissionRepository;
    private final UserCompanyRepository userCompanyRepository;
    private final CompanyPositionsRepository companyPositionsRepository;
    private final ObjectMapper objectMapper;

    public PositionService(PositionRepository positionRepository,
                           PositionPermissionRepository positionPermissionRepository,
                           UserCompanyRepository userCompanyRepository,
                           CompanyPositionsRepository companyPositionsRepository,
                           ObjectMapper objectMapper) {
        this.positionRepository = positionRepository;
        this.positionPermissionRepository = positionPermissionRepository;
        this.userCompanyRepository = userCompanyRepository;
        this.companyPositionsRepository = companyPositionsRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public PositionResponseDTO save(PositionRequestDTO dto) {
        Position position = new Position();
        position.setName(dto.getName());
        position.setAccesses(dto.getAccesses());

        return toResponse(positionRepository.save(position));
    }

    @Transactional
    public PositionResponseDTO update(UUID id, PositionRequestDTO dto) {
        Position position = positionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cargo com id:" + id + " não encontrado(a) para atualização"));

        position.setName(dto.getName());
        position.setAccesses(dto.getAccesses());

        return toResponse(positionRepository.save(position));
    }

    @Transactional
    public void deleteById(UUID id) {
        if (!positionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cargo com id:" + id + " não encontrado(a) para exclusão");
        }

        if (userCompanyRepository.existsByPositionId(id) || companyPositionsRepository.existsByPositionId(id)) {
            throw new ResourceInUseException("Cargo não pode ser excluído(a): possui vínculo(s) de empresa/usuário");
        }

        positionPermissionRepository.deleteByPositionId(id);
        positionRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public PositionResponseDTO findById(UUID id) {
        Position position = positionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cargo não encontrado(a) com ID: " + id));
        return toResponse(position);
    }

    @Transactional(readOnly = true)
    public List<PositionResponseDTO> findAll() {
        return positionRepository.findAll().stream().map(this::toResponse).toList();
    }

    private PositionResponseDTO toResponse(Position position) {
        return objectMapper.convertValue(position, PositionResponseDTO.class);
    }
}
