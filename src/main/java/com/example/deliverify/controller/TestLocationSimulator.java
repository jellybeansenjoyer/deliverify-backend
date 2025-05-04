package com.example.deliverify.controller;

import com.example.deliverify.dto.LocationUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestLocationSimulator {

    private final SimpMessagingTemplate messagingTemplate;

    @GetMapping("/simulate")
    public String simulate() throws InterruptedException {
        String orderId = "123";
        for (int i = 0; i < 10; i++) {
            double lat = 12.9716 + i * 0.0001;
            double lon = 77.5946 + i * 0.0001;

            LocationUpdateDTO update = new LocationUpdateDTO();
            update.setOrderId(orderId);
            update.setLatitude(lat);
            update.setLongitude(lon);

            messagingTemplate.convertAndSend(
                    "/topic/location/track/order/" + orderId, update
            );

            Thread.sleep(1000);  // simulate movement every second
        }
        return "Simulation complete";
    }
}
