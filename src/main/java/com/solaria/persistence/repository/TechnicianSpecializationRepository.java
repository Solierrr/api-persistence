package com.solaria.persistence.repository;

import com.solaria.persistence.domain.entity.TechnicianSpecialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TechnicianSpecializationRepository extends JpaRepository<TechnicianSpecialization, UUID> {
}
