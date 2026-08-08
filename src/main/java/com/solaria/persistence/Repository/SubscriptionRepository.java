package com.solaria.persistence.Repository;

import com.solaria.persistence.Domain.Entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {


    @Modifying
    @Query(value = "CALL sp_generate_subscription_charge(:subscriptionId, :dueDate, :paymentMethod)",
            nativeQuery = true)
    void callGenerateSubscriptionCharge(@Param("subscriptionId") UUID subscriptionId,
                                         @Param("dueDate") LocalDate dueDate,
                                         @Param("paymentMethod") String paymentMethod);


    @Modifying
    @Query(value = "CALL sp_mark_subscriptions_in_debt()", nativeQuery = true)
    void callMarkSubscriptionsInDebt();

    List<Subscription> findBySupplier_Company_Id(UUID companyId);

    Optional<Subscription> findByIdAndSupplier_Company_Id(UUID id, UUID companyId);

    List<Subscription> findBySupplierId(UUID supplierId);

    boolean existsByPlanId(UUID planId);

    @org.springframework.data.jpa.repository.Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END " +
            "FROM Subscription s WHERE s.supplier.id = :supplierId AND s.status = com.solaria.persistence.Domain.enums.SubscriptionStatus.PAID " +
            "AND (s.endDate IS NULL OR s.endDate > :now)")
    boolean hasActiveSubscription(@Param("supplierId") UUID supplierId, @Param("now") OffsetDateTime now);
}
