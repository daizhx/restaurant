package com.smtech.restaurant.order;

import com.smtech.restaurant.entities.Food;
import com.smtech.swing.common.btns.XButton;
import com.smtech.swing.common.panel.PagerGridView;
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
        PagerGridView v = new PagerGridView();
//        v.setOrientation(LinearLayout.HORIZONTAL);
        v.setBackground(Color.YELLOW);

        for(int i=0;i<20;i++) {
            XButton btn = new XButton();
            btn.setSize(0,80);
            btn.setText("按钮" + i);
            v.add(btn);
        }
        return v;
    }


}
