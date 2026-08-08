package com.solaria.persistence.Repository;

import com.solaria.persistence.Domain.Entity.Model;
import com.solaria.persistence.Domain.enums.ModelStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface ModelRepository extends JpaRepository<Model, UUID> {

    List<Model> findByStatus(ModelStatus status);
}
