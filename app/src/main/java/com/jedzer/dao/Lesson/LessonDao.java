package com.jedzer.dao.Lesson;

import com.jedzer.dao.User.IUserDao;
import com.jedzer.dao.exception.LessonAlreadyCreatedException;
import com.jedzer.dao.exception.LessonNotFoundException;
import com.jedzer.dao.exception.UserAlreadyCreatedException;
import com.jedzer.dao.exception.UserNotFoundException;
import com.jedzer.model.Lesson;
import com.jedzer.model.User;

import java.io.IOException;

public class LessonDao implements ILessonDao {

    private static ILessonDao Instance;

    private LessonDao() { Instance = LessonDaoSQLite.getInstance(); };

    public static ILessonDao getInstance()
    {
        return Instance;
    }

    @Override
    public void save(Lesson lesson) throws LessonAlreadyCreatedException, IOException {
        Instance.save(lesson);
    }

    @Override
    public Lesson getById(long id) throws LessonNotFoundException, IOException {
        return Instance.getById(id);
    }
}
