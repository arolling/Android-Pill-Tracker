package com.epicodus.pilltracker.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.epicodus.pilltracker.R;
import com.epicodus.pilltracker.adapters.PrescriptionPagerAdapter;
import com.epicodus.pilltracker.models.Prescription;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PrescriptionDetailActivity extends BaseActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private PrescriptionPagerAdapter adapterViewPager;
    ArrayList<Prescription> mPrescriptions = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.replaceContentLayout(R.layout.activity_prescription_detail, R.id.flContent);
        ButterKnife.bind(this);

        mPrescriptions = Parcels.unwrap(getIntent().getParcelableExtra("prescriptions"));
        int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));
        adapterViewPager = new PrescriptionPagerAdapter(getSupportFragmentManager(), mPrescriptions);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
