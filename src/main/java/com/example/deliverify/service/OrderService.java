package com.example.deliverify.service;

// com.example.deliverify.service.OrderService.java

import com.example.deliverify.entity.DeliveryAgent;
import com.example.deliverify.entity.Location;
import com.example.deliverify.entity.Order;
import com.example.deliverify.entity.OrderStatus;
import com.example.deliverify.repository.DeliveryAgentRepository;
import com.example.deliverify.repository.OrderRepository;
import com.example.deliverify.utils.DistanceUtil;
import com.example.deliverify.utils.GeoUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final DeliveryAgentRepository deliveryAgentRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public Order placeOrder(Order order) {
        // Set default order status
        order.setStatus(OrderStatus.PLACED);
        order.setOrderTime(LocalDateTime.now());

        // Save order first
        Order savedOrder = orderRepository.save(order);

        // Auto-assign delivery agent
        DeliveryAgent agent = findNearestAvailableAgent(order.getRestaurant().getLocation());

        if (agent == null) {
            throw new RuntimeException("No delivery agents available currently");
        }

        // Assign agent
        agent.setAvailable(false);
        savedOrder.setDeliveryAgent(agent);
        orderRepository.save(savedOrder);

        // Notify delivery agent
        messagingTemplate.convertAndSend(
                "/topic/agent/" + agent.getId(),
                "New order assigned! Order ID: " + savedOrder.getId()
        );

        return savedOrder;
    }

    private DeliveryAgent findNearestAvailableAgent(Location restaurantLoc) {
        List<DeliveryAgent> agents = deliveryAgentRepository.findByIsAvailableTrue();

        DeliveryAgent nearest = null;
        double minDistance = Double.MAX_VALUE;

        for (DeliveryAgent agent : agents) {
            if (agent.getUser() == null || agent.getUser().getLocation() == null) continue;
            Location agentLoc = agent.getUser().getLocation();

            double distance = GeoUtils.calculateDistance(
                    restaurantLoc.getLatitude(), restaurantLoc.getLongitude(),
                    agentLoc.getLatitude(), agentLoc.getLongitude()
            );

            if (distance < minDistance) {
                minDistance = distance;
                nearest = agent;
            }
        }

        return nearest;
    }
}
