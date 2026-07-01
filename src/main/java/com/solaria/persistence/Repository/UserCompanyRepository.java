package com.solaria.persistence.Repository;

import com.solaria.persistence.Entity.UserCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserCompanyRepository extends JpaRepository<UserCompany, UUID> {
}
