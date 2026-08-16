package com.solaria.persistence.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solaria.persistence.dto.request.InternalUserProvisionRequestDTO;
import com.solaria.persistence.dto.response.InternalUserProvisionResponseDTO;
import com.solaria.persistence.service.InternalUserProvisioningService;

import jakarta.validation.Valid;

// Controller M2M chamado por api-auth (via service token) para garantir que um User com o authId
// do evento USER_REGISTERED exista em dbsolier
@RestController
@RequestMapping("/internal/users")
public class InternalUserProvisioningController {

    private final InternalUserProvisioningService provisioningService;

    public InternalUserProvisioningController(InternalUserProvisioningService provisioningService) {
        this.provisioningService = provisioningService;
    }

    // Cria/confirma User com o authId recebido -> sempre responde 200 
    // idempotencia da operação garantida por authId 
    @PostMapping
    public ResponseEntity<InternalUserProvisionResponseDTO> provision(
            @Valid @RequestBody InternalUserProvisionRequestDTO dto) {
        return ResponseEntity.ok(provisioningService.provision(dto.getAuthId()));
    }
}
