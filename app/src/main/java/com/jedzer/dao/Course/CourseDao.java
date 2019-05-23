package com.jedzer.dao.Course;

import com.jedzer.dao.User.IUserDao;
import com.jedzer.dao.exception.UserAlreadyCreatedException;
import com.jedzer.dao.exception.UserNotFoundException;
import com.jedzer.model.User;

import java.io.IOException;

public class CourseDao implements IUserDao {

    private static IUserDao Instance;

    private CourseDao() { Instance = CourseDaoSQLite.getInstance(); };

    public static IUserDao getInstance()
    {
        return Instance;
    }

    @Override
    public void save(User user) throws UserAlreadyCreatedException, IOException {
        Instance.save(user);
    }

    @Override
    public User getByNickname(String nickname) throws UserNotFoundException, IOException {
        return Instance.getByNickname(nickname);
    }
}
