package com.example.deliverify.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "delivery_agents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryAgent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vehicleNumber;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean isAvailable;

    @Embedded
    private Location currentLocation;
}
