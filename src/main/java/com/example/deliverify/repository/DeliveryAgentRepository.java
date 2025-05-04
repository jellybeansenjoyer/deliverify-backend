package com.example.deliverify.repository;

import com.example.deliverify.entity.DeliveryAgent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryAgentRepository extends JpaRepository<DeliveryAgent, Long> {
    Optional<DeliveryAgent> findByUserId(Long userId);
    List<DeliveryAgent> findByIsAvailableTrue();
}
