package com.solaria.persistence.repository;

import com.solaria.persistence.domain.entity.Requester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RequesterRepository extends JpaRepository<Requester, UUID> {
}
