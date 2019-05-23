package com.jedzer.dao.Quiz;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;

import com.jedzer.adapters.SQLiteQuiz;
import com.jedzer.dao.exception.QuizAlreadyCreatedException;
import com.jedzer.dao.exception.QuizNotFoundException;
import com.jedzer.learnero.LearneroApp;
import com.jedzer.model.Quiz;

public class QuizDaoSQLite implements IQuizDao {
    private static final QuizDaoSQLite Instance = new QuizDaoSQLite();

    private QuizDaoSQLite() { };

    public static QuizDaoSQLite getInstance()
    {
        return Instance;
    }

    @Override
    public void save(Quiz quiz) throws QuizAlreadyCreatedException {
        SQLiteQuiz squ = new SQLiteQuiz(LearneroApp.getAppContext());
        SQLiteDatabase db;
        try
        {
            db = squ.getWritableDatabase();
        }
        catch (SQLException ex)
        {
            db = squ.getReadableDatabase();
        }
        Cursor cursor = db.query(
                SQLiteQuiz.TABLE_NAME,
                new String[] { SQLiteQuiz.KEY_ID },
                SQLiteQuiz.KEY_ID + " = ?",
                new String[] { Long.toString(quiz.getId()) },
                null,
                null,
                null
        );

        if (cursor.getCount() > 0)
        {
            throw new QuizAlreadyCreatedException();
        }
        cursor.close();

        ContentValues newUserValues = new ContentValues();
        newUserValues.put(SQLiteQuiz.KEY_TITLE, quiz.getTitle());
        newUserValues.put(SQLiteQuiz.KEY_TEXT, quiz.getText());
        newUserValues.put(SQLiteQuiz.KEY_IMAGE_ID, quiz.getImageId());
        newUserValues.put(SQLiteQuiz.KEY_LESSON_ID, quiz.getLessonId());
        db.insert(
                SQLiteQuiz.TABLE_NAME,
                null,
                newUserValues
        );
        db.close();
    }

    @Override
    public Quiz getById(long id) throws QuizNotFoundException {
        SQLiteQuiz dbu = new SQLiteQuiz(LearneroApp.getAppContext());
        SQLiteDatabase db;
        try {
            db = dbu.getWritableDatabase();
        }
        catch (SQLException ex)
        {
            db = dbu.getReadableDatabase();
        }
        Cursor cursor = db.query(
                SQLiteQuiz.TABLE_NAME,
                new String[] {
                        SQLiteQuiz.KEY_ID,
                        SQLiteQuiz.KEY_TEXT,
                        SQLiteQuiz.KEY_IMAGE_ID,
                        SQLiteQuiz.KEY_LESSON_ID,
                },
                SQLiteQuiz.KEY_ID + " = ?",
                new String[] { Long.toString(id), },
                null, null, null
        );

        if (cursor.getCount() == 0)
        {
            cursor.close();
            throw new QuizNotFoundException();
        }
        cursor.moveToNext();

        String title = " ";
        String text = " ";
        int imageId = -1;
        int lessonId = -1;

        int idIndex = cursor.getColumnIndex(SQLiteQuiz.KEY_ID);
        int titleIndex = cursor.getColumnIndex(SQLiteQuiz.KEY_TITLE);
        int textIndex = cursor.getColumnIndex(SQLiteQuiz.KEY_TEXT);
        int imageIdIndex = cursor.getColumnIndex(SQLiteQuiz.KEY_IMAGE_ID);
        int lessonIdIndex = cursor.getColumnIndex(SQLiteQuiz.KEY_LESSON_ID);

        id = cursor.getInt(idIndex);
        title = cursor.getString(titleIndex);
        text = cursor.getString(textIndex);
        imageId = cursor.getInt(imageIdIndex);
        lessonId = cursor.getInt(lessonIdIndex);
        cursor.close();

        return new Quiz(id, title, text, imageId, lessonId);

    }
}
