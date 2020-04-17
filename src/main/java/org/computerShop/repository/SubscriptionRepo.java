package org.computerShop.repository;

import org.computerShop.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepo extends JpaRepository<Subscription, Long> {
    boolean existsByEmail(String email);
}
