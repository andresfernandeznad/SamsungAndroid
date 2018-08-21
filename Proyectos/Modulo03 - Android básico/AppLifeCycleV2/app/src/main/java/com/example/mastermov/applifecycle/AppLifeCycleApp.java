package com.example.mastermov.applifecycle;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

/**
 * Created by corbera on 13/10/15.
 */
public class AppLifeCycleApp extends Application {
    private final String TAG = "APDM_"+this.getClass().getSimpleName();

    private int count = 0;

    @Override
    public void onCreate() {
        // First, call the parent class
        super.onCreate();

        // This is a place to put code that must manage storage across
        // multiple activities, but it's better to keep most things in a
        // database, rather than in memory
        Log.i(TAG, "onCreate");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.i(TAG, "onTerminate");

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        // In-memory caches should be thrown overboard here
        Log.i(TAG, "onLowMemory");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG, "onConifgurationChanged");
    }

    public void incCount() {
        count ++;
    }

    public int getCount() {
        return count;
    }
}
