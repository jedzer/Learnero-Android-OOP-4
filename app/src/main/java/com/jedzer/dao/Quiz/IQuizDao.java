package com.jedzer.dao.Quiz;

import com.jedzer.dao.exception.QuizAlreadyCreatedException;
import com.jedzer.dao.exception.QuizNotFoundException;
import com.jedzer.model.Quiz;

import java.io.IOException;
import java.util.List;

public interface IQuizDao {
    public void save(Quiz lesson) throws QuizAlreadyCreatedException, IOException;
    public Quiz getById(long id) throws QuizNotFoundException, IOException;
}
