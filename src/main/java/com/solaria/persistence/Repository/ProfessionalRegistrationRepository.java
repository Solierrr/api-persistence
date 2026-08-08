package com.solaria.persistence.Repository;

import com.solaria.persistence.Domain.Entity.ProfessionalRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface ProfessionalRegistrationRepository extends JpaRepository<ProfessionalRegistration, UUID> {

    List<ProfessionalRegistration> findByTechnicianId(UUID technicianId);

    boolean existsByProfessionId(UUID professionId);
}
