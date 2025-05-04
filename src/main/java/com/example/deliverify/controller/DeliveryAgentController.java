package com.example.deliverify.controller;

// com.example.deliverify.controller.DeliveryAgentController.java

import com.example.deliverify.entity.Location;
import com.example.deliverify.service.DeliveryAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agents")
public class DeliveryAgentController {

    @Autowired
    private DeliveryAgentService deliveryAgentService;

    @PostMapping("/{id}/location")
    public ResponseEntity<?> updateLocation(@PathVariable Long id, @RequestBody Location location) {
        deliveryAgentService.updateLocation(id, location);
        return ResponseEntity.ok("Location updated");
    }
}
