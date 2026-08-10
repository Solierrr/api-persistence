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

import com.solaria.persistence.dto3.patch.ServiceAcceptanceDTO;
import com.solaria.persistence.dto3.patch.ServiceScheduleDTO;
import com.solaria.persistence.dto3.patch.UpdatePurposeDTO;
import com.solaria.persistence.dto3.request.TechnicalServiceRequestDTO;
import com.solaria.persistence.dto3.response.TechnicalServiceResponseDTO;
import com.solaria.persistence.openapi.TechnicalServiceOpenApi;
import com.solaria.persistence.service.TechnicalServiceService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/technical-services")
@RequiredArgsConstructor
public class TechnicalServiceController implements TechnicalServiceOpenApi {

    private final TechnicalServiceService technicalServiceService;

    @Override
    @PostMapping
    public ResponseEntity<TechnicalServiceResponseDTO> save(@Valid @RequestBody TechnicalServiceRequestDTO dto) {
        TechnicalServiceResponseDTO response = technicalServiceService.save(dto);
        return ResponseEntity.created(URI.create("/api/technical-services/" + response.getId())).body(response);
    }

    @Override
    @PatchMapping("/{id}/purpose")
    public ResponseEntity<TechnicalServiceResponseDTO> updatePurpose(@PathVariable UUID id,
                                                                       @Valid @RequestBody UpdatePurposeDTO dto) {
        return ResponseEntity.ok(technicalServiceService.updatePurpose(id, dto.getPurpose()));
    }

    @Override
    @PatchMapping("/{id}/schedule")
    public ResponseEntity<TechnicalServiceResponseDTO> reschedule(@PathVariable UUID id,
                                                                    @Valid @RequestBody ServiceScheduleDTO dto) {
        return ResponseEntity.ok(technicalServiceService.reschedule(id, dto.getScheduledDate()));
    }

    @Override
    @PostMapping("/{id}/acceptance")
    public ResponseEntity<TechnicalServiceResponseDTO> accept(@PathVariable UUID id,
                                                               @Valid @RequestBody ServiceAcceptanceDTO dto) {
        return ResponseEntity.ok(technicalServiceService.accept(id, dto.getAcceptedBy()));
    }

    @Override
    @PostMapping("/{id}/completion")
    public ResponseEntity<TechnicalServiceResponseDTO> complete(@PathVariable UUID id) {
        return ResponseEntity.ok(technicalServiceService.complete(id));
    }

    @Override
    @PostMapping("/{id}/cancellation")
    public ResponseEntity<TechnicalServiceResponseDTO> cancel(@PathVariable UUID id) {
        return ResponseEntity.ok(technicalServiceService.cancel(id));
    }

    @Override
    @GetMapping("/{id}/company/{companyId}")
    public ResponseEntity<TechnicalServiceResponseDTO> findById(@PathVariable UUID id, @PathVariable UUID companyId) {
        return ResponseEntity.ok(technicalServiceService.findById(id, companyId));
    }

    @Override
    @GetMapping("/technical-project/{technicalProjectId}")
    public ResponseEntity<List<TechnicalServiceResponseDTO>> findByTechnicalProject(@PathVariable UUID technicalProjectId) {
        return ResponseEntity.ok(technicalServiceService.findByTechnicalProject(technicalProjectId));
    }

    @Override
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<TechnicalServiceResponseDTO>> findAllByCompany(@PathVariable UUID companyId) {
        return ResponseEntity.ok(technicalServiceService.findAllByCompany(companyId));
    }
}
