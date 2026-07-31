package com.solaria.persistence.repository;

import com.solaria.persistence.domain.entity.Charge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChargeRepository extends JpaRepository<Charge, UUID> {
}
