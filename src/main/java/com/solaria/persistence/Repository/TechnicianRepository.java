package com.solaria.persistence.Repository;

import com.solaria.persistence.Domain.Entity.Technician;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TechnicianRepository extends JpaRepository<Technician, UUID> {

    boolean existsByPersonId(UUID personId);
}
