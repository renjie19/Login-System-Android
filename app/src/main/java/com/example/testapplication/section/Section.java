package com.example.testapplication.section;


import java.io.Serializable;

import io.realm.RealmObject;

public class Section extends RealmObject implements Serializable {
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
}
