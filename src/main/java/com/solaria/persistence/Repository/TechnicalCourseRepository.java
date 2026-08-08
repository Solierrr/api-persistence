package com.solaria.persistence.Repository;

import com.solaria.persistence.Domain.Entity.TechnicalCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface TechnicalCourseRepository extends JpaRepository<TechnicalCourse, UUID> {

    List<TechnicalCourse> findByCompanyId(UUID companyId);

    Optional<TechnicalCourse> findByIdAndCompanyId(UUID id, UUID companyId);
}
