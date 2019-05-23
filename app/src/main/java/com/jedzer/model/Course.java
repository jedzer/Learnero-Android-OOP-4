package com.jedzer.model;

public class Course {
    private long id;
    private String title;
    private String description;
    private int imageId;
    private int[] lessonIds;

    public Course(long id, String title, String description, int imageId, int[] lessonIds) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageId = imageId;
        this.lessonIds= lessonIds;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int[] getLessonIds() {
        return lessonIds;
    }

    public void setLessonIds(int[] lessonIds) {
        this.lessonIds = lessonIds;
    }
}
