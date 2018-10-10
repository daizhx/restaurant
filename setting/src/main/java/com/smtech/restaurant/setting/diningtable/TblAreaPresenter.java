package com.smtech.restaurant.setting.diningtable;

import com.smtech.restaurant.entities.DiningTableArea;
import com.smtech.restaurant.setting.BeanPresenter;

public class TblAreaPresenter extends BeanPresenter<DiningTableArea> {

    @Override
    protected String loadDataApi() {
        return "/dining_table_area/all";
    }


    @Override
    protected String addBeanApi() {
        return "/dining_table_area/add";
    }

    @Override
    protected String updateBeanApi() {
        return "/dining_table_area/update";
    }

    @Override
    protected String deleteBeanApi(int id) {
        return "/dining_table_area/delete/" + id;
    }

}
