package com.example.deliverify.dto;

// com.example.deliverify.dto.AgentLocationDTO.java

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgentLocationDTO {
    private Long agentId;
    private double latitude;
    private double longitude;
}
