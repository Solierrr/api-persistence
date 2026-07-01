package com.solaria.persistence.Repository;

import com.solaria.persistence.Entity.ServiceContract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceContractRepository extends JpaRepository<ServiceContract, UUID> {
}
