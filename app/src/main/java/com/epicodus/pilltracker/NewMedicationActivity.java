package com.epicodus.pilltracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class NewMedicationActivity extends AppCompatActivity {
    public static final String TAG = NewMedicationActivity.class.getSimpleName();
    private ArrayList<String> mUserInfo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_medication);

        Intent intent = getIntent();
        mUserInfo = intent.getStringArrayListExtra("userInfo");
        Log.v(TAG, "User info: " + android.text.TextUtils.join(", ", mUserInfo));
    }
}
