package com.share.mvvm_architecture;

import android.app.Application;

import timber.log.Timber;

public class GreenCloudApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
    }
}
