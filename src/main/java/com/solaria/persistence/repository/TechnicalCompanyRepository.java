package com.solaria.persistence.repository;

import com.solaria.persistence.domain.entity.TechnicalCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TechnicalCompanyRepository extends JpaRepository<TechnicalCompany, UUID> {
}
