package com.smtech.restaurant.server.service.impl;

import com.smtech.restaurant.entities.Food;
import com.smtech.restaurant.entities.FoodType;
import com.smtech.restaurant.server.dao.FoodRepository;
import com.smtech.restaurant.server.dao.FoodTypeRepository;
import com.smtech.restaurant.server.service.FoodManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodManageServiceImpl implements FoodManageService {

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    FoodTypeRepository foodTypeRepository;

    @Override
    public FoodType addFoodType(FoodType ft) {
        return foodTypeRepository.save(ft);
    }

    @Override
    public void addFoodType(List<FoodType> foodTypes) {

    }

    @Override
    public void delFoodType(FoodType ft) {

    }

    @Override
    public void delFoodType(List<FoodType> foodTypes) {

    }

    @Override
    public void updateFoodType(FoodType ft) {

    }

    @Override
    public List<FoodType> getAllFoodType() {
        return foodTypeRepository.findAll();
    }

    @Override
    public void addFood(Food f) {

    }

    @Override
    public void addFood(List<Food> foods) {

    }

    @Override
    public void delFood(Food f) {

    }

    @Override
    public void delFood(List<Food> foods) {

    }

    @Override
    public void updateFood(Food f) {

    }

    @Override
    public List<Food> getAllFood() {
        return foodRepository.findAll();
    }
}
