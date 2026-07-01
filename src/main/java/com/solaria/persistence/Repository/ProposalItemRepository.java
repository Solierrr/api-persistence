package com.solaria.persistence.Repository;

import com.solaria.persistence.Entity.ProposalItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProposalItemRepository extends JpaRepository<ProposalItem, UUID> {
}
