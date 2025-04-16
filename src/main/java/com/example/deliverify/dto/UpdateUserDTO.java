package com.example.deliverify.dto;

import com.example.deliverify.entity.Location;
import lombok.Data;

@Data
public class UpdateUserDTO {
    private String name;
    private String phoneNumber;
    private Location location; // Optional location update
}