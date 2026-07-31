package com.solaria.persistence.repository;

import com.solaria.persistence.domain.entity.ProposalUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProposalUnitRepository extends JpaRepository<ProposalUnit, UUID> {
}
