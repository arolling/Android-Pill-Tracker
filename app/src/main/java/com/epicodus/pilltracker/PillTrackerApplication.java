package com.epicodus.pilltracker;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by abigailrolling on 5/6/16.
 */
public class PillTrackerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
