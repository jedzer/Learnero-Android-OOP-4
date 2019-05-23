package com.jedzer.model;

import com.orm.SugarRecord;

import java.io.Serializable;

public class Quiz extends SugarRecord<Quiz> {
    private String title;
    private String text;
    private String imageName;
    private Lesson lesson;

    public Quiz() {
    }

    public Quiz(String title, String text, String imageName, Lesson lesson) {
        this.title = title;
        this.text = text;
        this.imageName = imageName;
        this.lesson = lesson;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
