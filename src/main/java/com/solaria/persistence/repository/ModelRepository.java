package com.solaria.persistence.repository;

import com.solaria.persistence.domain.entity.Model;
import com.solaria.persistence.domain.enums.ModelStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface ModelRepository extends JpaRepository<Model, UUID> {

    List<Model> findByStatus(ModelStatus status);
}
