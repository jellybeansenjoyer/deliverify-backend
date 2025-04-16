package com.example.deliverify.dto;

import com.example.deliverify.entity.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private Role role; // CUSTOMER, ADMIN, DELIVERY_AGENT
}
