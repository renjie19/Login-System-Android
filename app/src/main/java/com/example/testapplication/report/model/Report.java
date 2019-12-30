package com.example.testapplication.report.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.testapplication.timelog.model.TimeLog;

import java.io.Serializable;

import io.realm.RealmObject;

public class Report implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeParcelable(this.timeInLog, flags);
        dest.writeParcelable(this.timeOutLog, flags);
        dest.writeDouble(this.totalHours);
    }

    public Report() {
    }

    protected Report(Parcel in) {
        this.id = in.readInt();
        this.timeInLog = in.readParcelable(TimeLog.class.getClassLoader());
        this.timeOutLog = in.readParcelable(TimeLog.class.getClassLoader());
        this.totalHours = in.readDouble();
    }

    public static final Parcelable.Creator<Report> CREATOR = new Parcelable.Creator<Report>() {
        @Override
        public Report createFromParcel(Parcel source) {
            return new Report(source);
        }

        @Override
        public Report[] newArray(int size) {
            return new Report[size];
        }
    };
}
