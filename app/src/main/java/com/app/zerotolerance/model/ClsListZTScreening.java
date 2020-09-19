package com.app.zerotolerance.model;

import java.util.List;

public class ClsListZTScreening {
    private String message;
    private List<ClsListDetailZTScreening> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ClsListDetailZTScreening> getData() {
        return data;
    }

    public void setData(List<ClsListDetailZTScreening> data) {
        this.data = data;
    }
}
