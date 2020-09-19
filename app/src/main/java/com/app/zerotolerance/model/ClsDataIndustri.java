package com.app.zerotolerance.model;

import com.app.zerotolerance.table.tbl_data_industri;

import java.util.List;

public class ClsDataIndustri {
    private String message;
    private List<ClsDetailDataIndustri> data;
    //private List<tbl_data_industri> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ClsDetailDataIndustri> getData() {
        return data;
    }

    public void setData(List<ClsDetailDataIndustri> data) {
        this.data = data;
    }
}
