package com.smtech.restaurant.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ComboFoodItemId implements Serializable {
    private int mainFoodId;
    private int subFoodId;

    public int getMainFoodId() {
        return mainFoodId;
    }

    public void setMainFoodId(int mainFoodId) {
        this.mainFoodId = mainFoodId;
    }

    public int getSubFoodId() {
        return subFoodId;
    }

    public void setSubFoodId(int subFoodId) {
        this.subFoodId = subFoodId;
    }
}
