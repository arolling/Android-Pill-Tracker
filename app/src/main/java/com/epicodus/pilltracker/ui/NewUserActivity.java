package com.epicodus.pilltracker.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.epicodus.pilltracker.Constants;
import com.epicodus.pilltracker.R;
import com.epicodus.pilltracker.models.User;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewUserActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = NewUserActivity.class.getSimpleName();
    @Bind(R.id.emailEditText) EditText mEmailEditText;
    @Bind(R.id.passwordEditText) EditText mPasswordEditText;
    @Bind(R.id.confirmPasswordEditText) EditText mConfirmPasswordEditText;
    @Bind(R.id.createNewAccountButton) Button mCreateNewAccountButton;
    @Bind(R.id.newUserNameEditText) EditText mUserName;
    @Bind(R.id.etDoctorName) EditText mDoctorName;
    @Bind(R.id.etDoctorPhone) EditText mDoctorPhone;
    @Bind(R.id.etPharmacyName) EditText mPharmacyName;
    @Bind(R.id.etPharmacyPhone) EditText mPharmacyPhone;
    private Firebase mFirebaseRef;
    private SharedPreferences.Editor mSharedPreferencesEditor;
    private SharedPreferences mSharedPreferences;
    private ProgressDialog mAuthProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        ButterKnife.bind(this);

        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
        mAuthProgressDialog.setCancelable(false);

        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);

        mCreateNewAccountButton.setOnClickListener(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mSharedPreferencesEditor = mSharedPreferences.edit();
    }

    @Override
    public void onClick(View v) {
        if (v == mCreateNewAccountButton) {
            createNewUser();
        }
    }

    public void createNewUser(){
        mAuthProgressDialog.show();
        final String name = mUserName.getText().toString();
        final String email = mEmailEditText.getText().toString();
        final String password = mPasswordEditText.getText().toString();
        final String confirmPassword = mConfirmPasswordEditText.getText().toString();
        final String doctorName = mDoctorName.getText().toString();
        final String doctorPhone = mDoctorPhone.getText().toString();
        final String pharmacyName = mPharmacyName.getText().toString();
        final String pharmacyPhone = mPharmacyPhone.getText().toString();

        boolean validEmail = isValidEmail(email);
        boolean validName = isValidName(name);
        boolean validPassword = isValidPassword(password, confirmPassword);
        if(!validEmail || !validName || !validPassword){
            mAuthProgressDialog.hide();
            return;
        }

        mFirebaseRef.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                String uid = result.get("uid").toString();
                createUserInFirebaseHelper(name, email, uid, doctorName, doctorPhone, pharmacyName, pharmacyPhone);
                mFirebaseRef.authWithPassword(email, password, new Firebase.AuthResultHandler() {

                    @Override
                    public void onAuthenticated(AuthData authData) {
                        mAuthProgressDialog.dismiss();
                        if (authData != null) {
                            String userUid = authData.getUid();

                            String userInfo = authData.toString();
                            Log.d(TAG, "Currently logged in: " + userInfo);

                            mSharedPreferencesEditor.putString(Constants.KEY_UID, userUid).apply();
                            mSharedPreferencesEditor.commit();
                            Intent intent = new Intent(NewUserActivity.this, NewMedicationActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        mAuthProgressDialog.dismiss();
                        switch (firebaseError.getCode()) {
                            case FirebaseError.INVALID_EMAIL:
                            case FirebaseError.USER_DOES_NOT_EXIST:
                                mEmailEditText.setError("Please check that you entered your email correctly");
                                break;
                            case FirebaseError.INVALID_PASSWORD:
                                mEmailEditText.setError(firebaseError.getMessage());
                                break;
                            case FirebaseError.NETWORK_ERROR:
                                showErrorToast("There was a problem with the network connection");
                                break;
                            default:
                                showErrorToast(firebaseError.toString());
                        }
                    }
                });
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                mAuthProgressDialog.dismiss();
                Log.d(TAG, "error occurred " +
                        firebaseError);
            }
        });
    }

    private void showErrorToast(String message) {
        Toast.makeText(NewUserActivity.this, message, Toast.LENGTH_LONG).show();
    }

    private void createUserInFirebaseHelper(final String name, final String email, final String uid, String doctorName, String doctorPhone, String pharmacyName, String pharmacyPhone) {
        final Firebase userLocation = new Firebase(Constants.FIREBASE_URL_USERS).child(uid);
        User newUser = new User(name, email, doctorName, doctorPhone, pharmacyName, pharmacyPhone);
        userLocation.setValue(newUser);
    }

    private boolean isValidEmail(String email){
        boolean isGoodEmail = (email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if(!isGoodEmail){
            mEmailEditText.setError("Please enter a valid email address");
        }
        return isGoodEmail;
    }

    private boolean isValidName(String name){
        if(name.equals("")){
            mUserName.setError("Please enter your name");
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password, String confirmPassword){
        if(password.length() < 6){
            mPasswordEditText.setError("Please create a password containing at least 6 characters");
            return false;
        } else if (!password.equals(confirmPassword)){
            mPasswordEditText.setError("Passwords do not match");
            return false;
        }
        return true;
    }

}
