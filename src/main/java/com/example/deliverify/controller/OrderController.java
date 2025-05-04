package com.example.deliverify.controller;

// com.example.deliverify.controller.OrderController.java

import com.example.deliverify.entity.Order;
import com.example.deliverify.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*") // Enable CORS for frontend on localhost
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @CrossOrigin(origins = "*") // Enable CORS for frontend on localhos
    public ResponseEntity<Order> placeOrder(@RequestBody Order orderRequest) {
        Order createdOrder = orderService.placeOrder(orderRequest);
        return ResponseEntity.ok(createdOrder);
    }
}
