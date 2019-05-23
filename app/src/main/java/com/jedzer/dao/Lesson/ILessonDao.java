package com.jedzer.dao.Lesson;

import com.jedzer.dao.exception.LessonAlreadyCreatedException;
import com.jedzer.dao.exception.LessonNotFoundException;
import com.jedzer.model.Lesson;

import java.io.IOException;
import java.util.List;

public interface ILessonDao {
    void save(Lesson lesson) throws LessonAlreadyCreatedException, IOException;
    Lesson getById(long id) throws LessonNotFoundException, IOException;
}
