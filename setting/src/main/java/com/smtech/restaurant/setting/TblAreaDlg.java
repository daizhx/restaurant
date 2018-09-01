package com.smtech.restaurant.setting;

import com.smtech.restaurant.entities.DiningTableArea;

public class TblAreaDlg extends DspBeanBaseDlg<DiningTableArea> {

    @Override
    protected String loadDataApi() {
        return "/dining_table_area/all";
    }

    @Override
    protected String[] getDspFields() {
        return new String[]{"name"};
    }

    @Override
    protected String[] getDspFieldTitles() {
        return new String[]{"名称"};
    }
}
