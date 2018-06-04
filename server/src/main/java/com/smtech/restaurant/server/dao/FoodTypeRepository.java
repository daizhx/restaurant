package com.smtech.restaurant.server.dao;

import com.smtech.restaurant.entities.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodTypeRepository extends JpaRepository<FoodType,Integer> {
}
