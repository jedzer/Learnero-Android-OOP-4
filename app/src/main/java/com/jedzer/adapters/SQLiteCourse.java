package com.jedzer.adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteCourse extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "COURSE";
    public static final String KEY_ID = "ID";
    public static final String KEY_TITLE = "TITLE";
    public static final String KEY_DESCRIPTION = "DESCRIPTION";
    public static final String KEY_IMAGE_ID = "IMAGE_ID";
    public static final String KEY_LESSON_IDS = "LESSON_IDS";

    private static final int DATABASE_VERSION = 1;


    public SQLiteCourse(Context context)
    {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME +
                "(" + KEY_ID + " integer primary key autoincrement, " +
                KEY_TITLE + " text, " +
                KEY_DESCRIPTION + " text, " +
                KEY_IMAGE_ID + " integer, " +
                KEY_LESSON_IDS + " text" +
                ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
