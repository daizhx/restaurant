package com.smtech.restaurant.server.dao;

import com.smtech.restaurant.entities.DiningTable;
import com.smtech.restaurant.entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiningTableRepository extends JpaRepository<DiningTable,Integer> {
}
