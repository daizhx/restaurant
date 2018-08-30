package com.smtech.restaurant.server.service;

import com.smtech.restaurant.entities.DiningTableArea;

import java.util.List;

public interface DinningTblAreaService {
    DiningTableArea add(DiningTableArea t);
    boolean del(int id);
    DiningTableArea get(int id);
    List<DiningTableArea> getAll();
    boolean update(DiningTableArea t);
}
