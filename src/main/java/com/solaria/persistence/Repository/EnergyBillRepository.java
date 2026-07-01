package com.solaria.persistence.Repository;

import com.solaria.persistence.Entity.EnergyBill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnergyBillRepository extends JpaRepository<EnergyBill, UUID> {
}
