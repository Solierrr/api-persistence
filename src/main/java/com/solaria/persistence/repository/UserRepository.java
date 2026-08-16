package com.solaria.persistence.repository;

import com.solaria.persistence.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Modifying
    @Query(value = "CALL sp_deactivate_inactive_users(:inactiveDays)", nativeQuery = true)
    void callDeactivateInactiveUsers(@Param("inactiveDays") int inactiveDays);
}
