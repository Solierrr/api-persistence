package com.solaria.persistence.repository;

import com.solaria.persistence.domain.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface CertificationRepository extends JpaRepository<Certification, UUID> {
}
