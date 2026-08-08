package com.solaria.persistence.Repository;

import com.solaria.persistence.Domain.Entity.Requester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RequesterRepository extends JpaRepository<Requester, UUID> {

    List<Requester> findByCompanyId(UUID companyId);

    Optional<Requester> findByIdAndCompanyId(UUID id, UUID companyId);

    boolean existsByCompanyId(UUID companyId);
}
