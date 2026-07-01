package com.solaria.persistence.Repository;

import com.solaria.persistence.Entity.TechnicalCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TechnicalCompanyRepository extends JpaRepository<TechnicalCompany, UUID> {
}
