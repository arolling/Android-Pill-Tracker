package com.epicodus.pilltracker.models;

/**
 * Created by abigailrolling on 5/6/16.
 */
public class User {
    private String name;
    private String email;
    private String doctorName;
    private String doctorPhone;
    private String pharmacyName;
    private String pharmacyPhone;

    public User() {}

    public User(String name, String email, String doctorName, String doctorPhone, String pharmacyName, String pharmacyPhone) {
        this.name = name;
        this.email = email;
        this.doctorName = doctorName;
        this.doctorPhone = doctorPhone;
        this.pharmacyName = pharmacyName;
        this.pharmacyPhone = pharmacyPhone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public String getPharmacyPhone() {
        return pharmacyPhone;
    }
}
