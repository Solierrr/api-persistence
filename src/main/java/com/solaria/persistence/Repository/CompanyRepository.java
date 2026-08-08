package com.solaria.persistence.Repository;

import com.solaria.persistence.Domain.Entity.Company;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface CompanyRepository extends JpaRepository<Company, UUID> {

    boolean existsByCnpj(String cnpj);

    Optional<Company> findByCnpj(String cnpj);

    boolean existsByAddressId(UUID addressId);

    boolean existsByBusinessContactId(UUID businessContactId);

    @Override
    @EntityGraph(attributePaths = {"address", "businessContact"})
    List<Company> findAll();
}
