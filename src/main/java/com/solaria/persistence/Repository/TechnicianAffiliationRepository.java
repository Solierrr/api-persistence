package com.solaria.persistence.Repository;

import com.solaria.persistence.Domain.Entity.TechnicianAffiliation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface TechnicianAffiliationRepository extends JpaRepository<TechnicianAffiliation, UUID> {

    List<TechnicianAffiliation> findByCompanyId(UUID companyId);

    Optional<TechnicianAffiliation> findByIdAndCompanyId(UUID id, UUID companyId);

    boolean existsByTechnicianIdAndActiveTrue(UUID technicianId);

    List<TechnicianAffiliation> findByTechnicianId(UUID technicianId);

    boolean existsByCompanyId(UUID companyId);

}
