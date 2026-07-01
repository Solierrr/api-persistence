package com.solaria.persistence.Repository;

import com.solaria.persistence.Entity.TechnicalService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TechnicalServiceRepository extends JpaRepository<TechnicalService, UUID> {
}
