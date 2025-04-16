package com.example.deliverify.service;

import com.example.deliverify.dto.UpdateUserDTO;
import com.example.deliverify.dto.UserDTO;
import com.example.deliverify.entity.User;
import com.example.deliverify.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDTO getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        return new UserDTO( user.getId(),
                user.getEmail(),
                user.getName(),
                user.getRole().name(),
                user.getPhoneNumber(),
                user.getLocation());
    }
    public UserDTO updateUserById(Long userId, UpdateUserDTO updateUserDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if (updateUserDTO.getName() != null) {
            user.setName(updateUserDTO.getName());
        }

        if (updateUserDTO.getPhoneNumber() != null) {
            user.setPhoneNumber(updateUserDTO.getPhoneNumber());
        }

        if (updateUserDTO.getLocation() != null) {
            user.setLocation(updateUserDTO.getLocation());
        }

        userRepository.save(user);

        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getRole().name(),
                user.getPhoneNumber(),
                user.getLocation()
        );
    }


    // You can also add updateCurrentUser(UpdateUserRequest request) here later.
}
