package com.solaria.persistence.Repository;

import com.solaria.persistence.Domain.Entity.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface ProfessionRepository extends JpaRepository<Profession, UUID> {
}
