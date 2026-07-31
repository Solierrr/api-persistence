package com.solaria.persistence.repository;

import com.solaria.persistence.domain.entity.LocalUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LocalUnitRepository extends JpaRepository<LocalUnit, UUID> {
}
