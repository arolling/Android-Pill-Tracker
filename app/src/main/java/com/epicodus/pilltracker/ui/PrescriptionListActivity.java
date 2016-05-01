package com.epicodus.pilltracker.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.epicodus.pilltracker.R;
import com.epicodus.pilltracker.models.Prescription;

import org.parceler.Parcels;

import java.util.ArrayList;

public class PrescriptionListActivity extends AppCompatActivity {
    public static final String TAG = PrescriptionListActivity.class.getSimpleName();

    ArrayList<Prescription> mAllPrescriptions = new ArrayList<>();


    private void setUpPrescriptions(){
        ArrayList<String> rx1Ingredient = new ArrayList<>();
        rx1Ingredient.add("atorvastatin calcium");
        Prescription rx1 = new Prescription(rx1Ingredient, "Lipitor", "20mg", "Take one tablet daily at bedtime", 1.0, 1.0, "Cholesterol reduction", "Oval, white");
        mAllPrescriptions.add(rx1);

        ArrayList<String> rx2Ingredient = new ArrayList<>();
        rx2Ingredient.add("olanzapine");
        Prescription rx2 = new Prescription(rx2Ingredient, "Zyprexa", "5mg", "Take one tablet daily at bedtime", 1.0, 1.0, "Sleeping", "White, round");
        mAllPrescriptions.add(rx2); // This is not medical advice and this drug should not be used for sleep

        ArrayList<String> rx3Ingredient = new ArrayList<>();
        rx3Ingredient.add("Brimonidine tartrate; timolol maleate");
        Prescription rx3 = new Prescription(rx3Ingredient, "Combigan", "0.2%;0.5%", "Instil 1 drop in each eye once daily", 1.0, 1.0, "Glaucoma", "Clear eyedrop bottle");
        mAllPrescriptions.add(rx3);

        ArrayList<String> rx4Ingredient = new ArrayList<>();
        rx4Ingredient.add("Hydrochlorothiazide; quinapril hydrochloride");
        Prescription rx4 = new Prescription(rx4Ingredient, "Accuretic", "12.5MG;EQ 20MG BASE", "Take one tablet twice daily", 2.0, 1.0, "Blood pressure", "Maroon, football shaped");
        mAllPrescriptions.add(rx4);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_list);

        setUpPrescriptions();
        Intent intent = getIntent();
        Prescription newPrescription = Parcels.unwrap(intent.getExtras().getParcelable("args"));
        mAllPrescriptions.add(newPrescription);

        Log.v(TAG, "rx count: " + mAllPrescriptions.size());
    }


}
