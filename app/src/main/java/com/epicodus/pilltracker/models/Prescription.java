package com.epicodus.pilltracker.models;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by abigailrolling on 4/29/16.
 */
@Parcel
public class Prescription {

    public List<String> activeIngredients;
    public String brandName;
    public String strength;
    public double dailyUsage; //calculated
    public String sig;
    public double frequency; //refactor to spinner - calculates per day float if < once per day (ie alendronate)
    public double qtyPerDose; //average if varies please
    public String quantityFilled;
    public String quantityRemaining; //calculated from current onhand, date, qt filled, current date, argh
    public Date dateFilled;
    public String indication; //why am I taking this?
    public String appearance;
    public Date firstPrescribed;
    public double currentOnhand;
    public Date dateOfCurrentOnhand;
    public List<String> questions; //for doctor or pharmacist
    public List<String> notes; //side effects, worries
    public String pushId;

    public Prescription(){

    }

    public Prescription(ArrayList<String> ingredients, String brandName, String strength, String sig, double frequency, double pillsPerDose, String indication, String appearance){
        this.activeIngredients = ingredients;
        this.brandName = brandName;
        this.strength = strength;
        this.sig = sig;
        this.frequency = frequency;
        this.qtyPerDose = pillsPerDose;
        this.indication = indication;
        this.appearance = appearance;
        this.questions = new ArrayList<>();
        this.notes = new ArrayList<>();
        this.dailyUsage = frequency * pillsPerDose;
    }

    public String showPrettyFrequency(){
        if(frequency == 1){
            return "To be taken once per day";
        } else if(frequency % 1 == 0){
            return "To be taken " + (int) frequency + " times per day";
        } else if(frequency * 7 >= 0.95) {
            return "To be taken " + (int)Math.round(frequency * 7) + " time(s) per week";
        } else {
            return "To be taken " + (int)Math.round(frequency * 30) + " time(s) per month";
        }
    }

    public String showPrettyQuantityPerDose(){
        return "Taking an average of " + qtyPerDose + " pill(s) per dose";
    }

    public String getSig() {
        return sig;
    }

    public String getStrength() {
        return strength;
    }

    public String getBrandName() {
        return brandName;
    }

    public List<String> getActiveIngredients() {
        return activeIngredients;
    }

    public String getAppearance() {
        return appearance;
    }

    public String getIndication() {
        return indication;
    }

    public double getQtyPerDose() {
        return qtyPerDose;
    }

    public double getDailyUsage() {
        return dailyUsage;
    }

    public double getFrequency() {
        return frequency;
    }

    public void addQuestion(String question){
        this.questions.add(question);
    }

    public void addNote(String note){
        this.notes.add(note);
    }

    public List<String> getQuestions() {
        return questions;
    }

    public List<String> getNotes() {
        return notes;
    }

    public String getQuantityFilled() {
        return quantityFilled;
    }

    public String getQuantityRemaining() {
        return quantityRemaining;
    }

    public Date getDateFilled() {
        return dateFilled;
    }

    public Date getFirstPrescribed() {
        return firstPrescribed;
    }

    public double getCurrentOnhand() {
        return currentOnhand;
    }

    public Date getDateOfCurrentOnhand() {
        return dateOfCurrentOnhand;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

}
