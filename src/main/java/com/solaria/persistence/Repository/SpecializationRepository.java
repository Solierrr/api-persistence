package com.solaria.persistence.repository;

import com.solaria.persistence.domain.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpecializationRepository extends JpaRepository<Specialization, UUID> {
}
