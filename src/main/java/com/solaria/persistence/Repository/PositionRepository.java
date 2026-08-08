package com.solaria.persistence.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solaria.persistence.Domain.Entity.Position;


public interface PositionRepository extends JpaRepository<Position, UUID> {
}
