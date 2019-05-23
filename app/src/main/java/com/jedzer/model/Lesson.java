package com.jedzer.model;

import com.orm.SugarRecord;

import java.io.Serializable;

public class Lesson extends SugarRecord<Lesson> {
    private String title;
    private String description;
    private String imageName;
    private Course course;

    public Lesson() {
    }

    public Lesson(String title, String description, String imageName, Course course) {
        this.title = title;
        this.description = description;
        this.imageName = imageName;
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
