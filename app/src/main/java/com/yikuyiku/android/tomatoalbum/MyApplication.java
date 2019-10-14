package com.yikuyiku.android.tomatoalbum;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;

public class MyApplication extends Application {
    private static Context context;
    private static ContentResolver contentResolver;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        contentResolver = context.getContentResolver();
    }

    public static Context globalContext() {
        return context;
    }

    public static ContentResolver globalContentResolver() { return contentResolver; }
}
