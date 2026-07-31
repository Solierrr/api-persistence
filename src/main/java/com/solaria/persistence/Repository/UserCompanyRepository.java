package com.solaria.persistence.repository;

import com.solaria.persistence.domain.entity.UserCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserCompanyRepository extends JpaRepository<UserCompany, UUID> {
}
