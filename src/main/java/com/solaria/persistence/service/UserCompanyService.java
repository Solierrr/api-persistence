package com.solaria.persistence.service3;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tools.jackson.databind.ObjectMapper;

import com.solaria.persistence.domain.entity.Company;
import com.solaria.persistence.domain.entity.Position;
import com.solaria.persistence.domain.entity.User;
import com.solaria.persistence.domain.entity.UserCompany;
import com.solaria.persistence.dto3.request.UserCompanyRequestDTO;
import com.solaria.persistence.dto3.response.PositionResponseDTO;
import com.solaria.persistence.dto3.response.UserCompanyResponseDTO;
import com.solaria.persistence.exception3.DuplicateResourceException;
import com.solaria.persistence.exception3.ResourceNotFoundException;
import com.solaria.persistence.exception3.UnauthorizedAccessException;
import com.solaria.persistence.repository.CompanyPositionsRepository;
import com.solaria.persistence.repository.CompanyRepository;
import com.solaria.persistence.repository.PositionRepository;
import com.solaria.persistence.repository.UserCompanyRepository;
import com.solaria.persistence.repository.UserRepository;


@Service
public class UserCompanyService {

    private final UserCompanyRepository userCompanyRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final PositionRepository positionRepository;
    private final CompanyPositionsRepository companyPositionsRepository;
    private final ObjectMapper objectMapper;

    public UserCompanyService(UserCompanyRepository userCompanyRepository,
                              UserRepository userRepository,
                              CompanyRepository companyRepository,
                              PositionRepository positionRepository,
                              CompanyPositionsRepository companyPositionsRepository,
                              ObjectMapper objectMapper) {
        this.userCompanyRepository = userCompanyRepository;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.positionRepository = positionRepository;
        this.companyPositionsRepository = companyPositionsRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public UserCompanyResponseDTO save(UserCompanyRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado com ID: " + dto.getUserId()));
        Company company = companyRepository.findById(dto.getCompanyId()).orElseThrow(
                () -> new ResourceNotFoundException("Empresa não encontrada com ID: " + dto.getCompanyId()));
        Position position = positionRepository.findById(dto.getPositionId()).orElseThrow(
                () -> new ResourceNotFoundException("Cargo não encontrado com ID: " + dto.getPositionId()));

        if (userCompanyRepository.existsByUserId(dto.getUserId())) {
            throw new DuplicateResourceException(
                    "Usuário já possui vínculo com uma empresa: só é permitido um vínculo por usuário");
        }

        if (!companyPositionsRepository.existsByCompanyIdAndPositionId(dto.getCompanyId(), dto.getPositionId())) {
            throw new UnauthorizedAccessException("Cargo não disponível para a empresa");
        }

        UserCompany userCompany = new UserCompany();
        userCompany.setUser(user);
        userCompany.setCompany(company);
        userCompany.setPosition(position);

        return toResponse(userCompanyRepository.save(userCompany));
    }

    @Transactional
    public UserCompanyResponseDTO updatePosition(UUID id, UUID positionId) {
        UserCompany userCompany = userCompanyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Vínculo usuário-empresa com id:" + id + " não encontrado(a) para atualização"));

        Position position = positionRepository.findById(positionId).orElseThrow(
                () -> new ResourceNotFoundException("Cargo não encontrado com ID: " + positionId));

        UUID companyId = userCompany.getCompany().getId();

        if (!companyPositionsRepository.existsByCompanyIdAndPositionId(companyId, positionId)) {
            throw new UnauthorizedAccessException("Cargo não disponível para a empresa");
        }

        userCompany.setPosition(position);

        return toResponse(userCompanyRepository.save(userCompany));
    }

    @Transactional
    public void deleteById(UUID id) {
        if (!userCompanyRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Vínculo usuário-empresa com id:" + id + " não encontrado para exclusão");
        }
        userCompanyRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public UserCompanyResponseDTO findById(UUID id) {
        UserCompany userCompany = userCompanyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Vínculo usuário-empresa não encontrado com ID: " + id));
        return toResponse(userCompany);
    }

    @Transactional(readOnly = true)
    public UserCompanyResponseDTO findById(UUID id, UUID companyId) {
        UserCompany userCompany = userCompanyRepository.findByIdAndCompanyId(id, companyId).orElseThrow(
                () -> new ResourceNotFoundException("Vínculo usuário-empresa não encontrado com ID: " + id));
        return toResponse(userCompany);
    }

    @Transactional(readOnly = true)
    public List<UserCompanyResponseDTO> findAllByCompany(UUID companyId) {
        return userCompanyRepository.findByCompanyId(companyId).
                stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<UserCompanyResponseDTO> findByUser(UUID userId) {
        return userCompanyRepository.findByUserId(userId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private UserCompanyResponseDTO toResponse(UserCompany entity) {
        UserCompanyResponseDTO response = objectMapper.convertValue(entity, UserCompanyResponseDTO.class);
        response.setCompanyId(entity.getCompany().getId());
        response.setUserId(entity.getUser().getId());
        response.setPosition(objectMapper.convertValue(entity.getPosition(), PositionResponseDTO.class));
        return response;
    }
}
