package com.jedzer.dao.Course;

import com.jedzer.model.Course;

import java.util.List;


public interface ICourseDao {
    public void save(Course course);
    public List<Course> getAll();
    public Course getById(long id);
}
