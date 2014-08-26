package com.wildsmith.material.core;

import android.app.Application;
import android.content.Context;

public class CoreMaterialApplication extends Application {

    private static CoreMaterialApplication application = null;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static Context getContext() {
        return application != null ? application.getBaseContext() : null;
    }
}