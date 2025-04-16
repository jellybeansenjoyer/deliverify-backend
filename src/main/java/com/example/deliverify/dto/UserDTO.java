package com.example.deliverify.dto;

import com.example.deliverify.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String name;
    private String role;
    private String phoneNumber;
    private Location location;
}
