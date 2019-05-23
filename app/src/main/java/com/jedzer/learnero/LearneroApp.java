package com.jedzer.learnero;

import android.app.Application;
import android.content.Context;

public class LearneroApp extends Application {
    private static Context context;

    public void onCreate()
    {
        super.onCreate();
        LearneroApp.context = getApplicationContext();
    }

    public static Context getAppContext()
    {
        return LearneroApp.context;
    }



}
