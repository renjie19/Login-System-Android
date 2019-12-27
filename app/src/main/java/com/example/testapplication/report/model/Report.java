package com.example.testapplication.report.model;

import com.example.testapplication.timelog.model.TimeLog;

import java.io.Serializable;

import io.realm.RealmObject;

public class Report implements Serializable {
    private int id;
    private TimeLog timeInLog;
    private TimeLog timeOutLog;
    private double totalHours;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TimeLog getTimeInLog() {
        return timeInLog;
    }

    public void setTimeInLog(TimeLog timeInLog) {
        this.timeInLog = timeInLog;
    }

    public TimeLog getTimeOutLog() {
        return timeOutLog;
    }

    public void setTimeOutLog(TimeLog timeOutLog) {
        this.timeOutLog = timeOutLog;
    }

    public double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
    }
}
