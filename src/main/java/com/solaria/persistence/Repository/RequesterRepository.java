package com.solaria.persistence.Repository;

import com.solaria.persistence.Entity.Requester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RequesterRepository extends JpaRepository<Requester, UUID> {
}
