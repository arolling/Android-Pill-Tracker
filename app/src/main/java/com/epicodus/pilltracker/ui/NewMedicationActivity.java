package com.epicodus.pilltracker.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
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
    private ArrayList<String> mNameSuggestions = new ArrayList<>();
    private ArrayList<String> mIngredients = new ArrayList<>();
    private boolean mBrand = false;
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

        mBrandGenericSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mBrand = true;
                } else {
                    mBrand = false;
                }
            }
        });

        mDrugSearchButton.setOnClickListener(this);
        mDrugConfirmButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.drugSearchButton:
                String search = mDrugNameSearch.getText().toString();
                getMatches(search);
                break;
            case R.id.drugConfirmButton:
                Log.v(TAG, "Brand: " + mBrand);
                if(mBrand){
                    String medication = mConfirmDrugSpinner.getSelectedItem().toString();
                    getActiveIngredients(medication);
                }
                // read switch for generic/brand, set generic/brand name (with api call if nec), receive drug strengths and populate spinner
                break;
            case R.id.addDrugButton:
                //gather all information and move to next activity
                break;
            default:
                break;
        }
    }

    private void getMatches(String search) {
        final DrugService drugService = new DrugService();
        drugService.autocompleteDrugs(search, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mNameSuggestions = drugService.processAutocomplete(response);
                NewMedicationActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayAdapter adapter = new ArrayAdapter(NewMedicationActivity.this, android.R.layout.simple_spinner_item, mNameSuggestions);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mConfirmDrugSpinner.setAdapter(adapter);
                    }
                });
            }
        });
    }

    private void getActiveIngredients(String drugName){
        final DrugService drugService = new DrugService();
        drugService.findIngredients(drugName, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response){
                mIngredients = drugService.processIngredients(response);
                Log.v(TAG, android.text.TextUtils.join(", ", mIngredients));
            }
        });

    }
}


