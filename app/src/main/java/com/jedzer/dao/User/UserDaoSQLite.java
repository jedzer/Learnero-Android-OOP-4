package com.jedzer.dao.User;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.jedzer.adapters.SQLiteUser;
import com.jedzer.dao.exception.UserAlreadyCreatedException;
import com.jedzer.dao.exception.UserNotFoundException;
import com.jedzer.learnero.LearneroApp;
import com.jedzer.model.User;

import java.util.List;

public class UserDaoSQLite implements IUserDao {
    private static final UserDaoSQLite Instance = new UserDaoSQLite();

    private UserDaoSQLite() { };

    public static UserDaoSQLite getInstance()
    {
        return Instance;
    }

    @Override
    public void save(User user) throws UserAlreadyCreatedException {
        SQLiteUser squ = new SQLiteUser(LearneroApp.getAppContext());
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
                SQLiteUser.TABLE_NAME,
                new String[] {SQLiteUser.KEY_NAME},
                SQLiteUser.KEY_NAME + " = ?",
                new String[] {user.getNickname()},
                null,
                null,
                null
        );

        if (cursor.getCount() > 0)
        {
            throw new UserAlreadyCreatedException();
        }
        cursor.close();

        ContentValues newUserValues = new ContentValues();
        newUserValues.put(SQLiteUser.KEY_NAME, user.getNickname());
        newUserValues.put(SQLiteUser.KEY_EMAIL, user.getEmail());
        newUserValues.put(SQLiteUser.KEY_PASSWORD, user.getPassword());
        newUserValues.put(SQLiteUser.KEY_REGISTRATION_DATE, user.getRegistrationDate());
        newUserValues.put(SQLiteUser.KEY_IS_PREMIUM, user.isPremium() ? 1 : 0);
        db.insert(
                SQLiteUser.TABLE_NAME,
                null,
                newUserValues
        );
        db.close();
    }

    @Override
    public User getByNickname(String login) throws UserNotFoundException {
        SQLiteUser dbu = new SQLiteUser(LearneroApp.getAppContext());
        SQLiteDatabase db;
        try {
            db = dbu.getWritableDatabase();
        }
        catch (SQLException ex)
        {
            db = dbu.getReadableDatabase();
        }
        Cursor cursor = db.query(
                SQLiteUser.TABLE_NAME,
                new String[] {
                        SQLiteUser.KEY_ID,
                        SQLiteUser.KEY_NAME,
                        SQLiteUser.KEY_PASSWORD,
                        SQLiteUser.KEY_EMAIL,
                        SQLiteUser.KEY_REGISTRATION_DATE,
                        SQLiteUser.KEY_IS_PREMIUM
                },
                "NAME = ?",
                new String[] { login },
                null, null, null
        );

        if (cursor.getCount() == 0)
        {
            cursor.close();
            throw new UserNotFoundException();
        }
        cursor.moveToNext();

        long id;
        String name = " ",
                email = " ",
                registrationDate = " ",
                password = " ";
        boolean isPremium;

        int idIndex = cursor.getColumnIndex(SQLiteUser.KEY_ID);
        int nameIndex = cursor.getColumnIndex(SQLiteUser.KEY_NAME);
        int passwordIndex = cursor.getColumnIndex(SQLiteUser.KEY_PASSWORD);
        int emailIndex = cursor.getColumnIndex(SQLiteUser.KEY_EMAIL);
        int regDateIndex = cursor.getColumnIndex(SQLiteUser.KEY_REGISTRATION_DATE);
        int isPremiumIndex = cursor.getColumnIndex(SQLiteUser.KEY_IS_PREMIUM);


        id = cursor.getInt(idIndex);
        name = cursor.getString(nameIndex);
        password = cursor.getString(passwordIndex);
        email = cursor.getString(emailIndex);
        registrationDate = cursor.getString(regDateIndex);
        isPremium = cursor.getInt(isPremiumIndex) == 1;
        cursor.close();

        return new User(id, name, email, password, registrationDate, isPremium);

    }
}
