package com.solaria.persistence.Repository;

import com.solaria.persistence.Domain.Entity.ProfessionalReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProfessionalReviewRepository extends JpaRepository<ProfessionalReview, UUID> {

    boolean existsByReviewer_IdAndProfessional_IdAndService_Id(UUID reviewerId, UUID professionalId, UUID serviceId);

    List<ProfessionalReview> findByProfessional_IdAndActiveTrue(UUID professionalId);

}
