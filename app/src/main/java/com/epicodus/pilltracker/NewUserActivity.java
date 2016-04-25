package com.epicodus.pilltracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewUserActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.addMedicationsButton) Button mAddMedsButton;
    @Bind(R.id.etUserName) EditText mUserName;
    @Bind(R.id.etDoctorName) EditText mDoctorName;
    @Bind(R.id.etDoctorAddress) EditText mDoctorAddress;
    @Bind(R.id.etPharmacyName) EditText mPharmacyName;
    @Bind(R.id.etPharmacyAddress) EditText mPharmacyAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        ButterKnife.bind(this);

        mAddMedsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mAddMedsButton) {
            Intent intent = new Intent(NewUserActivity.this, NewMedicationActivity.class);
            String userName = mUserName.getText().toString();
            String doctorName = mDoctorName.getText().toString();
            String doctorAddress = mDoctorAddress.getText().toString();
            String pharmacyName = mPharmacyName.getText().toString();
            String pharmacyAddress = mPharmacyAddress.getText().toString();
            ArrayList<String> userInfo = new ArrayList<>();
            userInfo.add(userName);
            userInfo.add(doctorName);
            userInfo.add(doctorAddress);
            userInfo.add(pharmacyName);
            userInfo.add(pharmacyAddress);
            intent.putStringArrayListExtra("userInfo", userInfo);
            startActivity(intent);
        }
    }
}
