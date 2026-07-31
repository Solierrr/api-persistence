package com.solaria.persistence.repository;

import com.solaria.persistence.domain.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
}
