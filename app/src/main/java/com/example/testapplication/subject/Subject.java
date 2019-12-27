package com.example.testapplication.subject;


import java.io.Serializable;

import io.realm.RealmObject;

public class Subject extends RealmObject implements Serializable {
    private int id;
    private String subjectName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
