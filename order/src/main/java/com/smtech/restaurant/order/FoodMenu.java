package com.smtech.restaurant.order;

import com.smtech.restaurant.entities.Food;
import com.smtech.restaurant.entities.FoodType;
import com.smtech.swing.common.panel.View;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by daizhx on 2018/5/22.
 *
 */
public class FoodMenu extends View{

    public interface OrderFoodListener{
        void onFoodOrdered(Food f);
    }

    private OrderFoodListener orderFoodListener;
    
    public OrderFoodListener getOrderFoodListener() {
        return orderFoodListener;
    }

    public void setOrderFoodListener(OrderFoodListener orderFoodListener) {
        this.orderFoodListener = orderFoodListener;
    }

    public FoodMenu() {
        setLayout(new BorderLayout());
        add(crtTypeView(),BorderLayout.NORTH);
        add(crtFoodListView(),BorderLayout.CENTER);
        init();
    }


    private void init() {

    }

    private Component crtFoodListView() {
        return null;
    }

    private Component crtTypeView() {
        return null;
    }


}
