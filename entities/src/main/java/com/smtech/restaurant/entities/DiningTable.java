package com.smtech.restaurant.entities;

import com.smtech.restaurant.enums.TableStatus;

import javax.persistence.*;

//桌台
@Entity
public class DiningTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @ManyToOne
    private DiningTableArea area;
    // 桌台状态
    private TableStatus status;

    public TableStatus getStatus() {
        return status;
    }

    public void setStatus(TableStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DiningTableArea getArea() {
        return area;
    }

    public void setArea(DiningTableArea area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "DiningTable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", area=" + area +
                '}';
    }
}
