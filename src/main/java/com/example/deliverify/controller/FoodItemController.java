package com.example.deliverify.controller;

import com.example.deliverify.dto.FoodItemDTO;
import com.example.deliverify.repository.FoodItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food-items")
@RequiredArgsConstructor
public class FoodItemController {

    private final FoodItemService foodItemService;

    @PostMapping
    public ResponseEntity<FoodItemDTO> create(@RequestBody FoodItemDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(foodItemService.create(dto));
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<FoodItemDTO>> getByRestaurant(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(foodItemService.getByRestaurant(restaurantId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodItemDTO> update(@PathVariable Long id, @RequestBody FoodItemDTO dto) {
        return ResponseEntity.ok(foodItemService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        foodItemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
