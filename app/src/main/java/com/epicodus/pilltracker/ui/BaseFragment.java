package com.epicodus.pilltracker.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;

import com.epicodus.pilltracker.Constants;
import com.firebase.client.Firebase;


public class BaseFragment extends Fragment {
    public SharedPreferences mSharedPreferences;
    public SharedPreferences.Editor mSharedPreferencesEditor;
    public Firebase mFirebaseRef;
    public String mUid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mSharedPreferencesEditor = mSharedPreferences.edit();
        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);
        mUid = mSharedPreferences.getString(Constants.KEY_UID, null);
    }


}
