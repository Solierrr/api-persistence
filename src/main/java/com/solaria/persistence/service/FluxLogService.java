package com.solaria.persistence.service3;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.solaria.persistence.domain.entity.FluxLog;
import com.solaria.persistence.domain.entity.User;
import com.solaria.persistence.dto3.request.FluxLogRequestDTO;
import com.solaria.persistence.dto3.response.FluxLogResponseDTO;
import com.solaria.persistence.exception3.ResourceNotFoundException;
import com.solaria.persistence.repository.FluxLogRepository;
import com.solaria.persistence.repository.UserRepository;


@Service
public class FluxLogService {

    private final FluxLogRepository fluxLogRepository;
    private final UserRepository userRepository;

    public FluxLogService(FluxLogRepository fluxLogRepository, UserRepository userRepository) {
        this.fluxLogRepository = fluxLogRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public FluxLogResponseDTO record(FluxLogRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuário não encontrado com ID: " + dto.getUserId()));

        if (!user.getActive()) {
            user.setActive(true);
            userRepository.save(user);
        }

        FluxLog fluxLog = new FluxLog();
        fluxLog.setUser(user);
        fluxLog.setAction(dto.getAction());
        fluxLog.setCreatedAt(OffsetDateTime.now());

        return toResponse(fluxLogRepository.save(fluxLog));
    }

    @Transactional(readOnly = true)
    public List<FluxLogResponseDTO> findByUser(UUID userId) {
        return fluxLogRepository.findByUserIdOrderByCreatedAtDesc(userId).stream()
                .map(this::toResponse)
                .toList();
    }

    private FluxLogResponseDTO toResponse(FluxLog entity) {
        FluxLogResponseDTO response = new FluxLogResponseDTO();
        response.setId(entity.getId());
        response.setUserId(entity.getUser().getId());
        response.setAction(entity.getAction());
        response.setCreatedAt(entity.getCreatedAt());
        return response;
    }
}
