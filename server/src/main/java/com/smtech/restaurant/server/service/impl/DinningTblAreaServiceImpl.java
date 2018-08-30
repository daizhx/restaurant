package com.smtech.restaurant.server.service.impl;

import com.smtech.restaurant.entities.DiningTableArea;
import com.smtech.restaurant.server.dao.DiningTableAreaRepository;
import com.smtech.restaurant.server.service.DinningTblAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DinningTblAreaServiceImpl implements DinningTblAreaService {

    @Autowired
    DiningTableAreaRepository repository;

    @Override
    public DiningTableArea add(DiningTableArea t) {
        return null;
    }

    @Override
    public boolean del(int id) {
        return false;
    }

    @Override
    public DiningTableArea get(int id) {
        return null;
    }

    @Override
    public List<DiningTableArea> getAll() {
        return repository.findAll();
    }

    @Override
    public boolean update(DiningTableArea t) {
        return false;
    }
}
