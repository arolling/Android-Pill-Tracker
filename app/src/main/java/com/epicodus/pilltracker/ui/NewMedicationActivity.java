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
import android.widget.TextView;

import com.epicodus.pilltracker.R;
import com.epicodus.pilltracker.models.Prescription;
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
    private ArrayList<String> mStrengths = new ArrayList<>();
    private String mMedication;
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
    @Bind(R.id.strengthInstructions) TextView mStrengthInstructions;
    @Bind(R.id.drugSearchInstructions) TextView mSearchInstructions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_medication);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mUserInfo = intent.getStringArrayListExtra("userInfo");
        Log.v(TAG, "User info: " + android.text.TextUtils.join(", ", mUserInfo));

        mDrugConfirmButton.setVisibility(View.GONE);
        mAddDrugButton.setVisibility(View.GONE);
        mConfirmDrugSpinner.setVisibility(View.GONE);
        mDrugStrengthSpinner.setVisibility(View.GONE);
        mBrandGenericSwitch.setVisibility(View.GONE);
        mIndicationEditText.setVisibility(View.GONE);
        mSigEditText.setVisibility(View.GONE);
        mStrengthInstructions.setVisibility(View.GONE);

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
        mAddDrugButton.setOnClickListener(this);
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
                String medName = mConfirmDrugSpinner.getSelectedItem().toString();
                if(medName.contains("'")){
                    mMedication = medName.substring(0, medName.indexOf("'"));
                } else {
                    mMedication = medName;
                }
                if(mBrand){
                    getActiveIngredients(mMedication);
                    getStrengths(mMedication);
                } else{
                    mIngredients.add(mMedication);
                    getStrengths(mMedication);
                }

                // read switch for generic/brand, set generic/brand name (with api call if nec), receive drug strengths and populate spinner
                break;
            case R.id.addDrugButton:
                String sig = mSigEditText.getText().toString();
                String indication = mIndicationEditText.getText().toString();
                String dose = mDrugStrengthSpinner.getSelectedItem().toString();
                Intent intent = new Intent(NewMedicationActivity.this, PrescriptionListActivity.class);
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
                        mConfirmDrugSpinner.setVisibility(View.VISIBLE);
                        mDrugConfirmButton.setVisibility(View.VISIBLE);

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

    private void getStrengths(String medication){
        final DrugService drugService = new DrugService();
        drugService.findStrengths(medication, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mStrengths = drugService.processStrengths(response);
                NewMedicationActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayAdapter adapter = new ArrayAdapter(NewMedicationActivity.this, android.R.layout.simple_spinner_item, mStrengths);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mDrugStrengthSpinner.setAdapter(adapter);

                        mDrugNameSearch.setVisibility(View.GONE);
                        mDrugSearchButton.setVisibility(View.GONE);
                        mDrugConfirmButton.setVisibility(View.GONE);
                        mConfirmDrugSpinner.setVisibility(View.GONE);
                        mSearchInstructions.setVisibility(View.GONE);

                        mStrengthInstructions.setVisibility(View.VISIBLE);
                        mDrugStrengthSpinner.setVisibility(View.VISIBLE);
                        mBrandGenericSwitch.setVisibility(View.VISIBLE);
                        mIndicationEditText.setVisibility(View.VISIBLE);
                        mSigEditText.setVisibility(View.VISIBLE);
                        mAddDrugButton.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }
}


