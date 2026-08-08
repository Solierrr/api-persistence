package com.solaria.persistence.Repository;

import com.solaria.persistence.Domain.Entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface CertificationRepository extends JpaRepository<Certification, UUID> {
}
