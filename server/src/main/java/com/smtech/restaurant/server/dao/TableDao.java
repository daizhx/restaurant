package com.smtech.restaurant.server.dao;

import com.smtech.restaurant.entities.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface TableDao extends JpaRepository<Table,Long> {

}
