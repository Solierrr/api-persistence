package com.solaria.persistence.repository;

import com.solaria.persistence.domain.entity.BusinessContact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BusinessContactRepository extends JpaRepository<BusinessContact, UUID> {
}
