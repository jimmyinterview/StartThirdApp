package com.malin.test;

import android.app.Application;


public class MApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new MyLifecycleHandler());
    }
}
