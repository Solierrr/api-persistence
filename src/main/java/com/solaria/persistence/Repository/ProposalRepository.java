package com.solaria.persistence.Repository;

import com.solaria.persistence.Entity.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProposalRepository extends JpaRepository<Proposal, UUID> {
}
