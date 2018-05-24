package com.smtech.restaurant.dao;

import com.smtech.restaurant.entities.DiningTable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by daizhx on 2018/3/28.
 */
public interface TableRepository extends JpaRepository<DiningTable,Long> {
}
