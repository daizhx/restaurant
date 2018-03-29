package com.smtech.restaurant.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class FoodOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date time;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

}
