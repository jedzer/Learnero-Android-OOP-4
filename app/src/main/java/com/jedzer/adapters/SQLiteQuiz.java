package com.jedzer.adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteQuiz extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "QUIZ";
    public static final String KEY_ID = "ID";
    public static final String KEY_TITLE = "TITLE";
    public static final String KEY_TEXT = "TEXT";
    public static final String KEY_LESSON_ID = "LESSON_ID";
    public static final String KEY_IMAGE_ID = "IMAGE_ID";

    private static final int DATABASE_VERSION = 1;


    public SQLiteQuiz(Context context)
    {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME +
                "(" + KEY_ID + " integer primary key autoincrement, " +
                KEY_TITLE + " text, " +
                KEY_TEXT + " text, " +
                KEY_LESSON_ID + " integer, " +
                KEY_IMAGE_ID + " integer" +
                ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
