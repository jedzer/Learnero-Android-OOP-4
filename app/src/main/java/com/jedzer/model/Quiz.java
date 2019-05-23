package com.jedzer.model;

public class Quiz {
    private long id;
    private String title;
    private String text;
    private int imageId;
    private int lessonId;

    public Quiz(long id, String title, String text, int imageId, int lessonId) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.imageId = imageId;
        this.lessonId = lessonId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
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

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
