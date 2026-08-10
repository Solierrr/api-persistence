package com.solaria.persistence.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solaria.persistence.dto3.patch.UpdateActiveDTO;
import com.solaria.persistence.dto3.patch.UpdateTypeDTO;
import com.solaria.persistence.dto3.request.TechnicianAffiliationRequestDTO;
import com.solaria.persistence.dto3.response.TechnicianAffiliationResponseDTO;
import com.solaria.persistence.openapi.TechnicianAffiliationOpenApi;
import com.solaria.persistence.service.TechnicianAffiliationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * controller REST (borda pura) para {@link com.solaria.persistence.domain.entity.TechnicianAffiliation}
 * (RN-14). Delega para {@link TechnicianAffiliationService}.
 */
@RestController
@RequestMapping("/api/technician-affiliations")
@RequiredArgsConstructor
public class TechnicianAffiliationController implements TechnicianAffiliationOpenApi {

    private final TechnicianAffiliationService technicianAffiliationService;

    @Override
    @PostMapping
    public ResponseEntity<TechnicianAffiliationResponseDTO> save(
            @Valid @RequestBody TechnicianAffiliationRequestDTO dto) {
        TechnicianAffiliationResponseDTO response = technicianAffiliationService.save(dto);
        return ResponseEntity.created(URI.create("/api/technician-affiliations/" + response.getId())).body(response);
    }

    @Override
    @PatchMapping("/{id}/type")
    public ResponseEntity<TechnicianAffiliationResponseDTO> updateType(@PathVariable UUID id,
                                                                         @Valid @RequestBody UpdateTypeDTO dto) {
        return ResponseEntity.ok(technicianAffiliationService.updateType(id, dto.getAffiliationType()));
    }

    @Override
    @PatchMapping("/{id}/active")
    public ResponseEntity<TechnicianAffiliationResponseDTO> updateActive(@PathVariable UUID id,
                                                                           @Valid @RequestBody UpdateActiveDTO dto) {
        return ResponseEntity.ok(technicianAffiliationService.updateActive(id, dto.getActive()));
    }

    @Override
    @GetMapping("/{id}/company/{companyId}")
    public ResponseEntity<TechnicianAffiliationResponseDTO> findById(@PathVariable UUID id,
                                                                       @PathVariable UUID companyId) {
        return ResponseEntity.ok(technicianAffiliationService.findById(id, companyId));
    }

    @Override
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<TechnicianAffiliationResponseDTO>> findAllByCompany(@PathVariable UUID companyId) {
        return ResponseEntity.ok(technicianAffiliationService.findAllByCompany(companyId));
    }

    @Override
    @GetMapping("/technician/{technicianId}")
    public ResponseEntity<List<TechnicianAffiliationResponseDTO>> findByTechnician(@PathVariable UUID technicianId) {
        return ResponseEntity.ok(technicianAffiliationService.findByTechnician(technicianId));
    }
}
