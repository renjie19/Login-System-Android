package com.example.testapplication.license;


import java.io.Serializable;

import io.realm.RealmObject;

public class License extends RealmObject implements Serializable {
    private int licenseId;
    private int licenseNumber;

    public int getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(int licenseId) {
        this.licenseId = licenseId;
    }

    public int getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(int licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}
