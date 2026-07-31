package com.solaria.persistence.repository;

import com.solaria.persistence.domain.entity.TechnicalService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TechnicalServiceRepository extends JpaRepository<TechnicalService, UUID> {
}
