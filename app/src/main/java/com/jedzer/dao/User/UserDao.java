package com.jedzer.dao.User;

import com.jedzer.dao.exception.UserAlreadyCreatedException;
import com.jedzer.dao.exception.UserNotFoundException;
import com.jedzer.model.User;

import java.io.IOException;
import java.util.List;

public class UserDao implements IUserDao {

    private static IUserDao Instance;

    private UserDao() { Instance = UserDaoSQLite.getInstance(); };

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
