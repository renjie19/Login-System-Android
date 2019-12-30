package com.example.testapplication.timelog.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.testapplication.utils.StateEnum;

import java.io.Serializable;


public class TimeLog implements Parcelable {
    private int id;
    private int employeeId;
    private StateEnum type;
    private long time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public StateEnum getType() {
        return type;
    }

    public void setType(StateEnum type) {
        this.type = type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.employeeId);
        dest.writeInt(this.type == null ? -1 : this.type.ordinal());
        dest.writeLong(this.time);
    }

    public TimeLog() {
    }

    protected TimeLog(Parcel in) {
        this.id = in.readInt();
        this.employeeId = in.readInt();
        int tmpType = in.readInt();
        this.type = tmpType == -1 ? null : StateEnum.values()[tmpType];
        this.time = in.readLong();
    }

    public static final Parcelable.Creator<TimeLog> CREATOR = new Parcelable.Creator<TimeLog>() {
        @Override
        public TimeLog createFromParcel(Parcel source) {
            return new TimeLog(source);
        }

        @Override
        public TimeLog[] newArray(int size) {
            return new TimeLog[size];
        }
    };
}
