package com.smtech.restaurant.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ComboFood {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private float price;

    @OneToMany(targetEntity = ComboFoodItem.class)
    @JoinColumn(name = "mainFoodId")
    private Set items;
}
