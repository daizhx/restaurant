package com.smtech.restaurant.server.service.impl;


import com.smtech.restaurant.entities.DiningTable;
import com.smtech.restaurant.server.dao.DiningTableRepository;
import com.smtech.restaurant.server.service.DinningTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DinningTableServiceImpl implements DinningTableService {

    @Autowired
    DiningTableRepository repository;

    @Override
    public DiningTable add(DiningTable t) {
        return null;
    }

    @Override
    public boolean del(int id) {
        return false;
    }

    @Override
    public DiningTable get(int id) {
        return null;
    }

    @Override
    public List<DiningTable> getAll() {
        return null;
    }

    @Override
    public boolean update(DiningTable t) {
        return false;
    }
}
