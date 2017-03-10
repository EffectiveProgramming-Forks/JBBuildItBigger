package com.udacity.gradle.builditbigger;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by jkbreunig on 3/9/17.
 */

public class BuildItBiggerApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.uprootAll();
            Timber.plant(new Timber.DebugTree());
        }
    }
}
