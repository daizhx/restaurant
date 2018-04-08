package com.smtech.restaurant.server.dao;

import com.smtech.restaurant.entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiningTableRepository extends JpaRepository<Food,Integer> {
}
