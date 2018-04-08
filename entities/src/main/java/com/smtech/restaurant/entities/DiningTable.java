package com.smtech.restaurant.entities;

import javax.persistence.*;

@Entity
public class DiningTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @ManyToOne
    private DiningTableArea area;


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
}
