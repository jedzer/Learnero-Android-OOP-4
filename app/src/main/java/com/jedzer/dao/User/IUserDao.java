package com.jedzer.dao.User;

import com.jedzer.dao.exception.UserAlreadyCreatedException;
import com.jedzer.dao.exception.UserNotFoundException;
import com.jedzer.model.User;

import java.io.IOException;
import java.util.List;

public interface IUserDao {
    public void save(User user) throws UserAlreadyCreatedException, IOException;
    public User getByNickname(String nickname) throws UserNotFoundException, IOException;
}
