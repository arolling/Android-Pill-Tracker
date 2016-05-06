package com.epicodus.pilltracker.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.pilltracker.models.Prescription;
import com.epicodus.pilltracker.ui.PrescriptionDetailFragment;

import java.util.ArrayList;

/**
 * Created by abigailrolling on 5/6/16.
 */
public class PrescriptionPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Prescription> mPrescriptions;


    public PrescriptionPagerAdapter(FragmentManager fm, ArrayList<Prescription> prescriptions){
        super(fm);
        mPrescriptions = prescriptions;
    }

    @Override
    public Fragment getItem(int position) {
        return PrescriptionDetailFragment.newInstance(mPrescriptions.get(position));
    }

    @Override
    public int getCount() {
        return mPrescriptions.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPrescriptions.get(position).getBrandName();
    }
}
