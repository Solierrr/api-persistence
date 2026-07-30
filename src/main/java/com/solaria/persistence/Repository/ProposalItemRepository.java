package com.solaria.persistence.repository;

import com.solaria.persistence.domain.entity.ProposalItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProposalItemRepository extends JpaRepository<ProposalItem, UUID> {
}
