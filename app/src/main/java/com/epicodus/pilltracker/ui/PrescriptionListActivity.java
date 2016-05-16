package com.epicodus.pilltracker.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.epicodus.pilltracker.Constants;
import com.epicodus.pilltracker.R;
import com.epicodus.pilltracker.adapters.FirebasePrescriptionListAdapter;
import com.epicodus.pilltracker.adapters.PrescriptionListAdapter;
import com.epicodus.pilltracker.models.Prescription;
import com.firebase.client.Firebase;
import com.firebase.client.Query;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PrescriptionListActivity extends BaseActivity {
    public static final String TAG = PrescriptionListActivity.class.getSimpleName();
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    private Query mQuery;
    private Firebase mFirebasePrescriptionsRef;
    private FirebasePrescriptionListAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.replaceContentLayout(R.layout.activity_prescription_list, R.id.flContent);

        ButterKnife.bind(this);

        mFirebasePrescriptionsRef = new Firebase(Constants.FIREBASE_URL_PRESCRIPTIONS);

        setUpFirebaseQuery();
        setUpRecyclerView();
    }

    private void setUpFirebaseQuery() {
        String userUid = mSharedPreferences.getString(Constants.KEY_UID, null);
        String location = mFirebasePrescriptionsRef.child(userUid).toString();
        mQuery = new Firebase(location);
    }

    private void setUpRecyclerView() {
        mAdapter = new FirebasePrescriptionListAdapter(mQuery, Prescription.class);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }


}
