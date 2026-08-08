package com.solaria.persistence.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tools.jackson.databind.ObjectMapper;
import com.solaria.persistence.DTO.Request.UserRequestDTO;
import com.solaria.persistence.DTO.Response.UserResponseDTO;
import com.solaria.persistence.Domain.Entity.User;
import com.solaria.persistence.Exception.InvalidFieldException;
import com.solaria.persistence.Exception.ResourceInUseException;
import com.solaria.persistence.Exception.ResourceNotFoundException;
import com.solaria.persistence.Repository.PersonRepository;
import com.solaria.persistence.Repository.UserCompanyRepository;
import com.solaria.persistence.Repository.UserRepository;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final UserCompanyRepository userCompanyRepository;
    private final ObjectMapper objectMapper;

    public UserService(UserRepository userRepository,
                        PersonRepository personRepository,
                        UserCompanyRepository userCompanyRepository,
                        ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.personRepository = personRepository;
        this.userCompanyRepository = userCompanyRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public UserResponseDTO save(UserRequestDTO dto) {
        User user = new User();
        user.setAuth_id(dto.getAuthId());
        user.setAvatar(dto.getAvatar());

        return toResponse(userRepository.save(user));
    }

    @Transactional
    public UserResponseDTO update(UUID id, UserRequestDTO dto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuário com id:" + id + " não encontrado para atualização"));
        if (!user.getAuth_id().equals(dto.getAuthId())) {
            throw new InvalidFieldException("ID de autenticação (authId) imutável: " + dto.getAuthId());
        }

        user.setAvatar(dto.getAvatar());

        return toResponse(userRepository.save(user));
    }

    @Transactional
    public void deleteById(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário com id:" + id + " não encontrado para exclusão");
        }
        if (personRepository.existsByUserId(id)) {
            throw new ResourceInUseException("Usuário não pode ser excluído: possui pessoa vinculada");
        }
        if (userCompanyRepository.existsByUserId(id)) {
            throw new ResourceInUseException("Usuário não pode ser excluído: possui vínculo(s) de empresa");
        }
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public UserResponseDTO findById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));
        return toResponse(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // Equivalente em lote/automático ao efeito colateral de FluxLogService.record: marca
    // active=false todo usuário sem ação registrada em flux_log nos últimos inactiveDays dias
    // (sp_deactivate_inactive_users, schema-v3-business-logic.sql). Requer essa procedure já
    // aplicada manualmente no Postgres.
    @Transactional
    public void deactivateInactiveUsers(int inactiveDays) {
        userRepository.callDeactivateInactiveUsers(inactiveDays);
    }

    private UserResponseDTO toResponse(User entity) {
        UserResponseDTO response = objectMapper.convertValue(entity, UserResponseDTO.class);
        response.setAuthId(entity.getAuth_id());
        return response;
    }
}
