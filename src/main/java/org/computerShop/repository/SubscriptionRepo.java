package org.computerShop.repository;

import org.computerShop.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepo extends JpaRepository<Subscription, Long> {
    boolean existsByEmail(String email);
}
