package com.solaria.persistence.Repository;

import com.solaria.persistence.Domain.Entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {

    List<Supplier> findByCompanyId(UUID companyId);

    Optional<Supplier> findByIdAndCompanyId(UUID id, UUID companyId);

    boolean existsByCompanyId(UUID companyId);
}
