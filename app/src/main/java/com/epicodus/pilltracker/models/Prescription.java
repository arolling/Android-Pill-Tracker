package com.epicodus.pilltracker.models;

import java.util.Date;

/**
 * Created by abigailrolling on 4/29/16.
 */
public class Prescription {

    public String mActiveIngredient;
    public String mBrandName;
    public String mStrength;
    public int mPillsPerDay; //calculated
    public String mSig;
    public int mFrequency; //spinner
    public double mPillsPerDose;
    public String mQuantityFilled;
    public String mQuantityRemaining; //calculated from currentonhand, date, qt filled, current date, argh
    public Date mDateFilled;
    public String mIndication; //why am I taking this?
    public String mAppearance;
    public Date mFirstPrescribed;
    public double mCurrentOnhand;
    public Date mDateOfCurrentOnhand;

    public Prescription(){

    }
}
