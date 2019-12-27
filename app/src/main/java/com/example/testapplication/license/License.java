package com.example.testapplication.license;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import io.realm.RealmObject;

public class License extends RealmObject implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.licenseId);
        dest.writeInt(this.licenseNumber);
    }

    public License() {
    }

    protected License(Parcel in) {
        this.licenseId = in.readInt();
        this.licenseNumber = in.readInt();
    }

    public static final Parcelable.Creator<License> CREATOR = new Parcelable.Creator<License>() {
        @Override
        public License createFromParcel(Parcel source) {
            return new License(source);
        }

        @Override
        public License[] newArray(int size) {
            return new License[size];
        }
    };
}
