package com.smtech.restaurant.setting;

import com.smtech.restaurant.entities.DiningTableArea;

public class TblAreaPresenter extends BeanPresenter<DiningTableArea> {

    @Override
    protected String loadDataApi() {
        return "/dining_table_area/all";
    }


    @Override
    protected String addBeanApi() {
        return "/dining_table_area/add";
    }

}
