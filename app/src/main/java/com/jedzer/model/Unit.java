package com.jedzer.model;

import com.orm.SugarRecord;

public class Unit extends SugarRecord<Unit> {
    private String title;

    public Unit() {
    }

    public Unit(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
