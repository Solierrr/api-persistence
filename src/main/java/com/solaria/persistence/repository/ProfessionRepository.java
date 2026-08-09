package com.solaria.persistence.repository;

import com.solaria.persistence.domain.entity.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface ProfessionRepository extends JpaRepository<Profession, UUID> {
}
