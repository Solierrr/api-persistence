package com.solaria.persistence.Repository;

import com.solaria.persistence.Entity.UnitSpecifications;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UnitSpecificationsRepository extends JpaRepository<UnitSpecifications, UUID> {
}
