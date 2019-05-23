package com.jedzer.model;

public class Lesson {
    private int id;
    private int courseId;
    private String title;
    private String description;
    private int imageId;
    private int[] quizIds;

    public Lesson(int id, String title, String description, int imageId, int courseId, int[] quizIds) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageId = imageId;
        this.courseId = courseId;
        this.quizIds = quizIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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

    public int[] getQuizIds() {
        return quizIds;
    }

    public void setQuizIds(int[] quizIds) {
        this.quizIds = quizIds;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
