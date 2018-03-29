package com.smtech.restaurant.server.dao;

import com.smtech.restaurant.entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food,Long>{
}
