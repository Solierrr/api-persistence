package com.solaria.persistence.repository;

import com.solaria.persistence.domain.entity.ServiceExecutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceExecutorRepository extends JpaRepository<ServiceExecutor, UUID> {
}
