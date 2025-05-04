package com.example.deliverify.controller;

import com.example.deliverify.dto.LocationUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5500")
public class LocationTrackingController {

    private final SimpMessagingTemplate messagingTemplate;
    @MessageMapping("/location/update") // Client sends here
    public void updateLocation(LocationUpdateDTO update) {
        // Send to: /topic/location/track/order/{orderId}
        messagingTemplate.convertAndSend(
                "/topic/location/track/order/" + update.getOrderId(),
                update
        );
    }
}
