package com.smtech.restaurant.entities;

import javax.persistence.*;

@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private float price;

    @Column(columnDefinition="boolean default false")
    private boolean guQing = false;

    private String tips;

    // 附加的菜品
//    @OneToMany(targetEntity = ComboFoodItem.class)
//    @JoinColumn(name = "mainFoodId")
//    private Set items;

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isGuQing() {
        return guQing;
    }

    public void setGuQing(boolean guQing) {
        this.guQing = guQing;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }
}
