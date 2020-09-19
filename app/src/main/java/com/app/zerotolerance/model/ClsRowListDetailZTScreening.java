package com.app.zerotolerance.model;

import java.util.List;

public class ClsRowListDetailZTScreening {
    private String message;
    private List<ClsRowDetailZTScreening> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ClsRowDetailZTScreening> getData() {
        return data;
    }

    public void setData(List<ClsRowDetailZTScreening> data) {
        this.data = data;
    }
}
