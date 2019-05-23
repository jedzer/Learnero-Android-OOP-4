package com.jedzer.model;

import com.orm.SugarRecord;

import java.io.Serializable;

public class Course extends SugarRecord<Course> {
    private String title;
    private String description;
    private String imageName;
    private String backGroundColor;
    private Unit unit;

    public Course() {
    }

    public Course(String title, String description, String imageName, String backGroundColor, Unit unit) {
        this.title = title;
        this.description = description;
        this.imageName = imageName;
        this.backGroundColor = backGroundColor;
        this.unit = unit;
    }

    public String getBackGroundColor() {
        return backGroundColor;
    }

    public void setBackGroundColor(String backGroundColor) {
        this.backGroundColor = backGroundColor;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
