package com.example.deliverify.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationUpdateDTO {
    private String orderId;
    private double latitude;
    private double longitude;
}
