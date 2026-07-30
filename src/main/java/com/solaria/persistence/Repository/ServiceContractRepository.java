package com.solaria.persistence.repository;

import com.solaria.persistence.domain.entity.ServiceContract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceContractRepository extends JpaRepository<ServiceContract, UUID> {
}
