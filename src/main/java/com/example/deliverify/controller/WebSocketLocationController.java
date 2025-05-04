package com.example.deliverify.controller;

// com.example.deliverify.controller.WebSocketLocationController.java

import com.example.deliverify.dto.AgentLocationDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5500")
@Controller
public class WebSocketLocationController {

    @MessageMapping("/location/broadcast") // client sends to /app/location/update
    @SendTo("/location/track") // broadcast to all clients subscribed to /location/track
    public AgentLocationDTO broadcastLocation(AgentLocationDTO agentLocation) {
        return agentLocation; // this could also persist to DB if needed
    }
}
