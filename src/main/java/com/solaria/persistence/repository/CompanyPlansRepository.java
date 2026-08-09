package com.solaria.persistence.repository;

import com.solaria.persistence.domain.entity.CompanyPlans;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface CompanyPlansRepository extends JpaRepository<CompanyPlans, UUID> {
}
