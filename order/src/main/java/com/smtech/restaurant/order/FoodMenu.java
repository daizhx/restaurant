package com.smtech.restaurant.order;

import com.smtech.restaurant.entities.Food;
import com.smtech.swing.common.panel.TransparentPanel;
import com.smtech.swing.common.panel.ViewGroup;

import java.awt.*;

/**
 * Created by daizhx on 2018/5/22.
 *
 */
public class FoodMenu extends ViewGroup {

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
        super();

        setLayout(new BorderLayout());
        add(crtTypeView(),BorderLayout.NORTH);
        add(crtFoodListView(),BorderLayout.CENTER);
        init();
    }


    private void init() {

    }

    private Component crtFoodListView() {
        ViewGroup v = new ViewGroup();
        return v;
    }

    private Component crtTypeView() {
        ViewGroup v = new ViewGroup();
        return v;
    }


}
