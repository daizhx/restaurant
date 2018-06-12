package com.smtech.restaurant.order;

import com.smtech.restaurant.common.HttpClient;
import com.smtech.restaurant.entities.Food;
import com.smtech.restaurant.entities.FoodType;
import com.smtech.swing.common.btns.BtnByDraw;
import com.smtech.swing.common.btns.XButton;
import com.smtech.swing.common.panel.PagerGridView;
import com.smtech.swing.common.panel.ViewGroup;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by daizhx on 2018/5/22.
 *
 */
public class FoodMenu extends ViewGroup {

    private ArrayList<FoodType> foodTypeList;

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
        //查询出类别
        HttpClient hc = HttpClient.getInstance();

    }

    private Component crtFoodListView() {
        PagerGridView v = new PagerGridView(5,6);
        v.setBackground(Color.WHITE);
        for(int i=0;i<59;i++) {
            FoodView fv = new FoodView();
            fv.setText("红烧排骨"+i);
            v.add(fv);
        }

        return v;
    }

    private Component crtTypeView() {
        PagerGridView v = new PagerGridView();
//        v.setOrientation(LinearLayout.HORIZONTAL);
        v.setBackground(Color.YELLOW);

        for(int i=0;i<20;i++) {
            XButton btn = new XButton();
            btn.setBackground(Color.GREEN);
            btn.setSize(0,80);
            btn.setText("按钮" + i);
            v.add(btn);
        }
        return v;
    }

    // 菜品视图
    private class FoodView extends BtnByDraw{

    }

}
