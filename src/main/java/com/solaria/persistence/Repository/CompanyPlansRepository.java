package com.solaria.persistence.Repository;

import com.solaria.persistence.Entity.CompanyPlans;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyPlansRepository extends JpaRepository<CompanyPlans, UUID> {
}
