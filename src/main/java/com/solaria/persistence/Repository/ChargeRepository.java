package com.solaria.persistence.Repository;

import com.solaria.persistence.Entity.Charge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChargeRepository extends JpaRepository<Charge, UUID> {
}
