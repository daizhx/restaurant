package com.smtech.restaurant.server.dao;

import com.smtech.restaurant.entities.FoodOrderBill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodOrderRepository extends JpaRepository<FoodOrderBill,Integer> {
}
