package com.solaria.persistence.repository;

import com.solaria.persistence.domain.entity.Technician;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TechnicianRepository extends JpaRepository<Technician, UUID> {
}
