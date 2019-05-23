package com.jedzer.dao.Lesson;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.jedzer.adapters.SQLiteLesson;
import com.jedzer.dao.exception.LessonAlreadyCreatedException;
import com.jedzer.dao.exception.LessonNotFoundException;
import com.jedzer.learnero.LearneroApp;
import com.jedzer.model.Lesson;
import com.jedzer.model.User;


public class LessonDaoSQLite implements ILessonDao {
    private static final LessonDaoSQLite Instance = new LessonDaoSQLite();

    private LessonDaoSQLite() { };

    public static LessonDaoSQLite getInstance()
    {
        return Instance;
    }

    @Override
    public void save(Lesson lesson) throws LessonAlreadyCreatedException {
        SQLiteLesson squ = new SQLiteLesson(LearneroApp.getAppContext());
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
                SQLiteLesson.TABLE_NAME,
                new String[] {SQLiteLesson.KEY_ID},
                SQLiteLesson.KEY_ID + " = ?",
                new String[] { Integer.toString(lesson.getId()) },
                null,
                null,
                null
        );

        if (cursor.getCount() > 0)
        {
            throw new LessonAlreadyCreatedException();
        }
        cursor.close();

        ContentValues newUserValues = new ContentValues();
        newUserValues.put(SQLiteLesson.KEY_TITLE, lesson.getTitle());
        newUserValues.put(SQLiteLesson.KEY_DESCRIPTION, lesson.getDescription());
        newUserValues.put(SQLiteLesson.KEY_IMAGE_ID, lesson.getImageId());
        newUserValues.put(SQLiteLesson.KEY_COURSE_ID, lesson.getCourseId());
        db.insert(
                SQLiteLesson.TABLE_NAME,
                null,
                newUserValues
        );
        db.close();
    }

    @Override
    public Lesson getById(long id) throws LessonNotFoundException {
        SQLiteLesson dbu = new SQLiteLesson(LearneroApp.getAppContext());
        SQLiteDatabase db;
        try {
            db = dbu.getWritableDatabase();
        }
        catch (SQLException ex)
        {
            db = dbu.getReadableDatabase();
        }
        Cursor cursor = db.query(
                SQLiteLesson.TABLE_NAME,
                new String[] {
                        SQLiteLesson.KEY_ID,
                        SQLiteLesson.KEY_TITLE,
                        SQLiteLesson.KEY_COURSE_ID,
                        SQLiteLesson.KE,
                        SQLiteLesson.KEY_REGISTRATION_DATE,
                        SQLiteLesson.KEY_IS_PREMIUM
                },
                "NAME = ?",
                new String[] { login },
                null, null, null
        );

        if (cursor.getCount() == 0)
        {
            cursor.close();
            throw new LessonNotFoundException();
        }
        cursor.moveToNext();


        String title = " ";
        String description = " ";
        int imageId = -1;
        int courseId = -1;
        int[] quizIds = { };


        int titleIndex = cursor.getColumnIndex(SQLiteLesson.KEY_TITLE);
        int descriptionIndex = cursor.getColumnIndex(SQLiteLesson.KEY_DESCRIPTION);
        int imageIdIndex = cursor.getColumnIndex(SQLiteLesson.KEY_IMAGE_ID);
        int courseIdIndex = cursor.getColumnIndex(SQLiteLesson.KEY_COURSE_ID);
        int quizIdsIndex = cursor.getColumnIndex(SQLiteLesson.KEY_QUIZ_IDS);


        title = cursor.getString(titleIndex);
        description = cursor.getString(descriptionIndex);
        imageId = cursor.getInt(imageIdIndex);
        courseId = cursor.getInt(courseIdIndex);
        quizIds = stringToQuizArray(cursor.getString(quizIdsIndex));
        cursor.close();

        return new Lesson((int)id, title, description, imageId, courseId, quizIds);

    }

    private int[] stringToQuizArray(String string) {

    }
}
