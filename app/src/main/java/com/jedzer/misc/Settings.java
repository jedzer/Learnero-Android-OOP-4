package com.jedzer.misc;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.jedzer.learnero.LearneroApp;


public class Settings {
    private static final String MAIN_PREFERENCES = "main_preferences";
    private static final String NICK_NAME = "nick_name";
    private static final String IS_LOGGED_IN = "is_logged_in";


    private static SharedPreferences getSharedPref()
    {
        Context context = LearneroApp.getAppContext();
        return context.getSharedPreferences(MAIN_PREFERENCES, Context.MODE_PRIVATE);
    }


    public static void setLogIn(String nickname)
    {
        SharedPreferences sPref = getSharedPref();
        SharedPreferences.Editor editor = sPref.edit();
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(NICK_NAME, nickname);
        editor.apply();
    }

    public static String getNickName()
    {
        SharedPreferences sPref = getSharedPref();
        return sPref.getString(NICK_NAME, "");
    }

    public static boolean isLoggedIn()
    {
        SharedPreferences sPref = getSharedPref();
        return sPref.getBoolean(IS_LOGGED_IN, false);
    }
}
