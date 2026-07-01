package com.solaria.persistence.Repository;

import com.solaria.persistence.Entity.ServiceExecutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceExecutorRepository extends JpaRepository<ServiceExecutor, UUID> {
}
