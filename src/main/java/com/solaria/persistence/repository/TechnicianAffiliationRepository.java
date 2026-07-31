package com.solaria.persistence.repository;

import com.solaria.persistence.domain.entity.TechnicianAffiliation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TechnicianAffiliationRepository extends JpaRepository<TechnicianAffiliation, UUID> {
}
