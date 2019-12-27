package com.example.testapplication.subject;


import android.os.Parcel;
import android.os.Parcelable;


import io.realm.RealmObject;

public class Subject extends RealmObject implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.subjectName);
    }

    public Subject() {
    }

    protected Subject(Parcel in) {
        this.id = in.readInt();
        this.subjectName = in.readString();
    }

    public static final Parcelable.Creator<Subject> CREATOR = new Parcelable.Creator<Subject>() {
        @Override
        public Subject createFromParcel(Parcel source) {
            return new Subject(source);
        }

        @Override
        public Subject[] newArray(int size) {
            return new Subject[size];
        }
    };
}
