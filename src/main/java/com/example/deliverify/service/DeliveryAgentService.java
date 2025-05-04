package com.example.deliverify.service;

import com.example.deliverify.entity.DeliveryAgent;
import com.example.deliverify.entity.Location;
import com.example.deliverify.repository.DeliveryAgentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryAgentService {
    @Autowired
    private DeliveryAgentRepository deliveryAgentRepository;

    public void updateLocation(Long agentId, Location newLocation) {
        DeliveryAgent agent = deliveryAgentRepository.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent not found"));

        agent.setCurrentLocation(newLocation);
        deliveryAgentRepository.save(agent);
    }
}
