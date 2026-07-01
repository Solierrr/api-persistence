package com.solaria.persistence.Repository;

import com.solaria.persistence.Entity.BusinessContact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BusinessContactRepository extends JpaRepository<BusinessContact, UUID> {
}
