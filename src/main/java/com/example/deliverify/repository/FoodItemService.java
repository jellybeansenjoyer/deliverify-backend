package com.example.deliverify.repository;

import com.example.deliverify.dto.FoodItemDTO;

import java.util.List;

public interface FoodItemService {
    FoodItemDTO create(FoodItemDTO dto);
    List<FoodItemDTO> getByRestaurant(Long restaurantId);
    FoodItemDTO update(Long id, FoodItemDTO dto);
    void delete(Long id);
}
