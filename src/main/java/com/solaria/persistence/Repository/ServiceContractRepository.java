package com.solaria.persistence.repository;

import com.solaria.persistence.domain.entity.ServiceContract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface ServiceContractRepository extends JpaRepository<ServiceContract, UUID> {

    Optional<ServiceContract> findByIdAndService_TechnicalProject_Requester_Company_Id(UUID id, UUID companyId);

    boolean existsByServiceId(UUID serviceId);

    Optional<ServiceContract> findByServiceId(UUID serviceId);
}
