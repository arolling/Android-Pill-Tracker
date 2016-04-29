package com.epicodus.pilltracker.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.epicodus.pilltracker.R;
import com.epicodus.pilltracker.services.DrugService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewMedicationActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = NewMedicationActivity.class.getSimpleName();
    private ArrayList<String> mUserInfo = new ArrayList<>();
    @Bind(R.id.drugSearchButton) Button mDrugSearchButton;
    @Bind(R.id.drugConfirmButton) Button mDrugConfirmButton;
    @Bind(R.id.addDrugButton) Button mAddDrugButton;
    @Bind(R.id.drugNameEditText) EditText mDrugNameSearch;
    @Bind(R.id.confirmDrugSpinner) Spinner mConfirmDrugSpinner;
    @Bind(R.id.drugStrengthSpinner) Spinner mDrugStrengthSpinner;
    @Bind(R.id.brandGenericSwitch) Switch mBrandGenericSwitch;
    @Bind(R.id.indicationEditText) EditText mIndicationEditText;
    @Bind(R.id.sigEditText) EditText mSigEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_medication);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mUserInfo = intent.getStringArrayListExtra("userInfo");
        Log.v(TAG, "User info: " + android.text.TextUtils.join(", ", mUserInfo));

        mDrugSearchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.drugSearchButton:
                String search = mDrugNameSearch.getText().toString();
                getRestaurants(search);
                //make api call & show list of possible drugs
                break;
            case R.id.drugConfirmButton:
                // read switch for generic/brand, set generic/brand name (with api call if nec), receive drug strengths and populate spinner
                break;
            case R.id.addDrugButton:
                //gather all information and move to next activity
                break;
            default:
                break;
        }
    }

    private void getRestaurants(String search) {
        DrugService drugService = new DrugService();
        drugService.findDrugs(search, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    if (response.isSuccessful()) {
                        Log.v(TAG, jsonData);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}


