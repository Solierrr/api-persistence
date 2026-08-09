package com.solaria.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solaria.persistence.domain.entity.Position;


public interface PositionRepository extends JpaRepository<Position, UUID> {
}
