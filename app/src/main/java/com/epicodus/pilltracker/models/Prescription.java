package com.epicodus.pilltracker.models;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by abigailrolling on 4/29/16.
 */
@Parcel
public class Prescription {

    public String mActiveIngredient;
    public String mBrandName;
    public String mStrength;
    public double mPillsPerDay; //calculated
    public String mSig;
    public double mFrequency; //spinner - calculates per day float if < once per day (ie alendronate)
    public double mPillsPerDose; //average if varies please
    public String mQuantityFilled;
    public String mQuantityRemaining; //calculated from currentonhand, date, qt filled, current date, argh
    public Date mDateFilled;
    public String mIndication; //why am I taking this?
    public String mAppearance;
    public Date mFirstPrescribed;
    public double mCurrentOnhand;
    public Date mDateOfCurrentOnhand;
    public ArrayList<String> mQuestions; //for doctor or pharmacist
    public ArrayList<String> mConcerns; //side effects, worries

    public Prescription(){

    }

    public Prescription(String ingredient, String brandName, String strength, String sig, double frequency, double pillsPerDose, String indication, String appearance){
        this.mActiveIngredient = ingredient;
        this.mBrandName = brandName;
        this.mStrength = strength;
        this.mSig = sig;
        this.mFrequency = frequency;
        this.mPillsPerDose = pillsPerDose;
        this.mIndication = indication;
        this.mAppearance = appearance;
        this.mQuestions = new ArrayList<>();
        this.mConcerns = new ArrayList<>();
        this.mPillsPerDay = frequency * pillsPerDose;
    }

    public String getmSig() {
        return mSig;
    }

    public String getmStrength() {
        return mStrength;
    }

    public String getmBrandName() {
        return mBrandName;
    }

    public String getmActiveIngredient() {
        return mActiveIngredient;
    }

    public String getmAppearance() {
        return mAppearance;
    }

    public String getmIndication() {
        return mIndication;
    }

    public double getmPillsPerDose() {
        return mPillsPerDose;
    }

    public double getmPillsPerDay() {
        return mPillsPerDay;
    }

    public double getmFrequency() {
        return mFrequency;
    }

    public void addQuestion(String question){
        this.mQuestions.add(question);
    }

    public void addConcern(String concern){
        this.mConcerns.add(concern);
    }

    public ArrayList<String> getmQuestions() {
        return mQuestions;
    }

    public ArrayList<String> getmConcerns() {
        return mConcerns;
    }
}
