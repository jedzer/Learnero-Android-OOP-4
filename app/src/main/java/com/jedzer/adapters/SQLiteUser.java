package com.jedzer.adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteUser extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "USER";
    public static final String KEY_ID = "ID";
    public static final String KEY_NAME = "NAME";
    public static final String KEY_EMAIL = "EMAIL";
    public static final String KEY_PASSWORD = "PASSWORD";
    public static final String KEY_REGISTRATION_DATE = "REGISTRATION_DATE";
    public static final String KEY_IS_PREMIUM = "IS_PREMIUM";

    private static final int DATABASE_VERSION = 1;


    public SQLiteUser(Context context)
    {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME +
                "(" + KEY_ID + " integer primary key autoincrement, " +
                KEY_NAME + " text, " +
                KEY_EMAIL + " text, " +
                KEY_PASSWORD + " text, " +
                KEY_REGISTRATION_DATE + " text" +
                ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
