package com.solaria.persistence.repository;

import com.solaria.persistence.domain.entity.UnitSpecifications;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UnitSpecificationsRepository extends JpaRepository<UnitSpecifications, UUID> {
}
