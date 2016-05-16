package com.epicodus.pilltracker.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.epicodus.pilltracker.Constants;
import com.epicodus.pilltracker.R;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    public static final String TAG = LoginActivity.class.getSimpleName();
    @Bind(R.id.passwordLoginButton) Button mPasswordLoginButton;
    @Bind(R.id.emailEditText) EditText mEmailEditText;
    @Bind(R.id.passwordEditText) EditText mPasswordEditText;
    @Bind(R.id.newUserButton) Button mNewUserButton;


    private ProgressDialog mAuthProgressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        checkLoginStatus();
        mPasswordLoginButton.setOnClickListener(this);

        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
        mAuthProgressDialog.setCancelable(false);

        mNewUserButton.setOnClickListener(this);

        String signupEmail = mSharedPreferences.getString(Constants.KEY_USER_EMAIL, null);
        if (signupEmail != null) {
            mEmailEditText.setText(signupEmail);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.newUserButton:
                Intent intent = new Intent(LoginActivity.this, NewUserActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.passwordLoginButton:
                loginWithPassword();
            default:
                break;
        }
    }

    private void loginWithPassword() {
        final String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        if(email.equals("")){
            mEmailEditText.setError("Please enter your email");
        } else if (password.equals("")){
            mPasswordEditText.setError("Password cannot be blank");
        } else {
            mAuthProgressDialog.show();

            mFirebaseRef.authWithPassword(email, password, new Firebase.AuthResultHandler(){

                @Override
                public void onAuthenticated(AuthData authData){
                    mAuthProgressDialog.dismiss();
                    mSharedPreferencesEditor.putString(Constants.KEY_USER_EMAIL, email).apply();
                    if(authData != null){
                        String userUid = authData.getUid();
                        mSharedPreferencesEditor.putString(Constants.KEY_UID, userUid).apply();
                        mSharedPreferencesEditor.commit();
                        String userInfo = authData.toString();
                        Log.d(TAG, "Currently logged in: " + userInfo);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onAuthenticationError(FirebaseError firebaseError){
                    mAuthProgressDialog.dismiss();
                    switch (firebaseError.getCode()) {
                        case FirebaseError.INVALID_EMAIL:
                        case FirebaseError.USER_DOES_NOT_EXIST:
                            mEmailEditText.setError("Please check that you entered your email correctly");
                            break;
                        case FirebaseError.INVALID_PASSWORD:
                            mPasswordEditText.setError(firebaseError.getMessage());
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
    }

    private void showErrorToast(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
    }

    public void checkLoginStatus(){
        AuthData authData = mFirebaseRef.getAuth();
        Log.v(TAG, "Authdata: " + authData);
        Log.v(TAG, "sharedpref: " + mSharedPreferences.getString(Constants.KEY_UID, null));
        if(authData != null && authData.getUid().equals(mSharedPreferences.getString(Constants.KEY_UID, null))){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }

}
