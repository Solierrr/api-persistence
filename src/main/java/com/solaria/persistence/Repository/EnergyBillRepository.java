package com.solaria.persistence.repository;

import com.solaria.persistence.domain.entity.EnergyBill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnergyBillRepository extends JpaRepository<EnergyBill, UUID> {
}
