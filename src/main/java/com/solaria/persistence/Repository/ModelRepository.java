package com.solaria.persistence.Repository;

import com.solaria.persistence.Entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ModelRepository extends JpaRepository<Model, UUID> {
}
