package com.solaria.persistence.Repository;

import com.solaria.persistence.Entity.ProposalUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProposalUnitRepository extends JpaRepository<ProposalUnit, UUID> {
}
