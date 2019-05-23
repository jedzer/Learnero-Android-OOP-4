package com.jedzer.dao.Quiz;

import com.jedzer.dao.exception.QuizAlreadyCreatedException;
import com.jedzer.dao.exception.QuizNotFoundException;
import com.jedzer.model.Quiz;

import java.io.IOException;

public class QuizDao implements IQuizDao {

    private static IQuizDao Instance;

    private QuizDao() { Instance = QuizDaoSQLite.getInstance(); };

    public static IQuizDao getInstance()
    {
        return Instance;
    }

    @Override
    public void save(Quiz user) throws QuizAlreadyCreatedException, IOException {
        Instance.save(user);
    }

    @Override
    public Quiz getById(long id) throws QuizNotFoundException, IOException {
        return Instance.getById(id);
    }
}
