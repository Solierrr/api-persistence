package com.solaria.persistence.repository;

import com.solaria.persistence.domain.entity.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProposalRepository extends JpaRepository<Proposal, UUID> {
}
