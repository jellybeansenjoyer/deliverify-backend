package com.example.deliverify.service;

import com.example.deliverify.dto.FoodItemDTO;
import com.example.deliverify.entity.FoodItem;
import com.example.deliverify.entity.Restaurant;
import com.example.deliverify.repository.FoodItemRepository;
import com.example.deliverify.repository.FoodItemService;
import com.example.deliverify.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodItemServiceImpl implements FoodItemService {

    private final FoodItemRepository foodItemRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public FoodItemDTO create(FoodItemDTO dto) {
        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        FoodItem foodItem = FoodItem.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .restaurant(restaurant)
                .build();

        return mapToDTO(foodItemRepository.save(foodItem));
    }

    @Override
    public List<FoodItemDTO> getByRestaurant(Long restaurantId) {
        return foodItemRepository.findByRestaurantId(restaurantId)
                .stream().map(this::mapToDTO).toList();
    }

    @Override
    public FoodItemDTO update(Long id, FoodItemDTO dto) {
        FoodItem foodItem = foodItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food item not found"));

        foodItem.setName(dto.getName());
        foodItem.setPrice(dto.getPrice());
        return mapToDTO(foodItemRepository.save(foodItem));
    }

    @Override
    public void delete(Long id) {
        foodItemRepository.deleteById(id);
    }

    private FoodItemDTO mapToDTO(FoodItem foodItem) {
        return FoodItemDTO.builder()
                .id(foodItem.getId())
                .name(foodItem.getName())
                .price(foodItem.getPrice())
                .restaurantId(foodItem.getRestaurant().getId())
                .build();
    }
}
