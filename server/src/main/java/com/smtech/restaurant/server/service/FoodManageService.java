package com.smtech.restaurant.server.service;

import com.smtech.restaurant.entities.Food;
import com.smtech.restaurant.entities.FoodType;

import java.util.List;

public interface FoodManageService {

    FoodType addFoodType(FoodType ft);

    void addFoodType(List<FoodType> foodTypes);

    void delFoodType(FoodType ft);

    void delFoodType(List<FoodType> foodTypes);

    void updateFoodType(FoodType ft);

    List<FoodType> getAllFoodType();

    void addFood(Food f);

    void addFood(List<Food> foods);

    void delFood(Food f);

    void delFood(List<Food> foods);

    void updateFood(Food f);

    List<Food> getAllFood();
}
