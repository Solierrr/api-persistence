package com.solaria.persistence.Repository;

import com.solaria.persistence.Entity.TechnicianAffiliation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TechnicianAffiliationRepository extends JpaRepository<TechnicianAffiliation, UUID> {
}
