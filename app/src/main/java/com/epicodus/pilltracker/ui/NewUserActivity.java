package com.epicodus.pilltracker.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.pilltracker.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewUserActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.createNewAccountButton) Button mCreateNewAccountButton;
    @Bind(R.id.newUserNameEditText) EditText mUserName;
    @Bind(R.id.etDoctorName) EditText mDoctorName;
    @Bind(R.id.etDoctorPhone) EditText mDoctorPhone;
    @Bind(R.id.etPharmacyName) EditText mPharmacyName;
    @Bind(R.id.etPharmacyPhone) EditText mPharmacyPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        ButterKnife.bind(this);

        mCreateNewAccountButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mCreateNewAccountButton) {
            Intent intent = new Intent(NewUserActivity.this, NewMedicationActivity.class);
            String userName = mUserName.getText().toString();
            String doctorName = mDoctorName.getText().toString();
            String doctorPhone = mDoctorPhone.getText().toString();
            String pharmacyName = mPharmacyName.getText().toString();
            String pharmacyPhone = mPharmacyPhone.getText().toString();
            ArrayList<String> userInfo = new ArrayList<>();
            userInfo.add(userName);
            userInfo.add(doctorName);
            userInfo.add(doctorPhone);
            userInfo.add(pharmacyName);
            userInfo.add(pharmacyPhone);
            intent.putStringArrayListExtra("userInfo", userInfo);
            startActivity(intent);
        }
    }
}
