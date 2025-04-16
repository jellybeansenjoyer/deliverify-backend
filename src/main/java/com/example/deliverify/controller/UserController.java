package com.example.deliverify.controller;

import com.example.deliverify.dto.UpdateUserDTO;
import com.example.deliverify.dto.UserDTO;
import com.example.deliverify.entity.User;
import com.example.deliverify.exception.UserNotFoundException;
import com.example.deliverify.repository.UserRepository;
import com.example.deliverify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<UserDTO>> getAllUsers(Pageable pageable) {
        Page<UserDTO> users = userRepository.findAll(pageable)
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getEmail(),
                        user.getName(),
                        user.getRole().name(),
                        user.getPhoneNumber(),
                        user.getLocation()
                ));
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'AGENT', 'CUSTOMER')")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

        UserDTO userDTO = new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getRole().name(),
                user.getPhoneNumber(),
                user.getLocation()
        );
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getMyProfile() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUserById(@PathVariable Long id, @RequestBody UpdateUserDTO updateUserDTO) {
        UserDTO updatedUser = userService.updateUserById(id, updateUserDTO);
        return ResponseEntity.ok(updatedUser);
    }





}
