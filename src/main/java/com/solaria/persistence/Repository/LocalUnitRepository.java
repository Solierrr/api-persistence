package com.solaria.persistence.Repository;

import com.solaria.persistence.Entity.LocalUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LocalUnitRepository extends JpaRepository<LocalUnit, UUID> {
}
