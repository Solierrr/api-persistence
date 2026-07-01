package com.solaria.persistence.Repository;

import com.solaria.persistence.Entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RolePermissionRepository extends JpaRepository<RolePermission, UUID> {
}
