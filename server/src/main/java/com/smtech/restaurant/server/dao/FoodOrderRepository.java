package com.smtech.restaurant.server.dao;

import com.smtech.restaurant.entities.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodOrderRepository extends JpaRepository<FoodOrder,Integer> {
}
