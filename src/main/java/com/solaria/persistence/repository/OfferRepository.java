package com.solaria.persistence.repository;

import com.solaria.persistence.domain.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OfferRepository extends JpaRepository<Offer, UUID> {
}
