package com.solaria.persistence.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solaria.persistence.Domain.Entity.PositionPermission;


public interface PositionPermissionRepository extends JpaRepository<PositionPermission, UUID> {

    boolean existsByPositionIdAndPermissionId(UUID positionId, UUID permissionId);

    List<PositionPermission> findByPositionId(UUID positionId);

    void deleteByPositionId(UUID positionId);

    void deleteByPermissionId(UUID permissionId);

}
