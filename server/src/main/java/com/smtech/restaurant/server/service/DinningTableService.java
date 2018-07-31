package com.smtech.restaurant.server.service;

import com.smtech.restaurant.entities.DiningTable;

import java.util.List;

public interface DinningTableService {
    DiningTable add(DiningTable t);
    boolean del(int id);
    DiningTable get(int id);
    List<DiningTable> getAll();
    boolean update(DiningTable t);
}
