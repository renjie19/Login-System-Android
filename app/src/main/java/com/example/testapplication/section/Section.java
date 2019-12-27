package com.example.testapplication.section;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import io.realm.RealmObject;

public class Section extends RealmObject implements Parcelable {
    private int sectionId;
    private String sectionName;
    private String yearLevel;

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getYearLevel() {
        return yearLevel;
    }

    public void setYearLevel(String yearLevel) {
        this.yearLevel = yearLevel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.sectionId);
        dest.writeString(this.sectionName);
        dest.writeString(this.yearLevel);
    }

    public Section() {
    }

    protected Section(Parcel in) {
        this.sectionId = in.readInt();
        this.sectionName = in.readString();
        this.yearLevel = in.readString();
    }

    public static final Parcelable.Creator<Section> CREATOR = new Parcelable.Creator<Section>() {
        @Override
        public Section createFromParcel(Parcel source) {
            return new Section(source);
        }

        @Override
        public Section[] newArray(int size) {
            return new Section[size];
        }
    };
}
