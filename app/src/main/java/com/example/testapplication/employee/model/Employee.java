package com.example.testapplication.employee.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.example.testapplication.license.License;
import com.example.testapplication.section.Section;
import com.example.testapplication.subject.Subject;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Employee extends RealmObject implements Parcelable {
    @PrimaryKey
    private int employeeId;
    private String name;
    private int age;
    private String address;
    private String position;
    private License license;
    private RealmList<Subject> subjectList;
    private RealmList<Section> sectionList;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(RealmList<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public List<Section> getSectionList() {
        return sectionList;
    }

    public void setSectionList(RealmList<Section> sectionList) {
        this.sectionList = sectionList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.employeeId);
        dest.writeString(this.name);
        dest.writeInt(this.age);
        dest.writeString(this.address);
        dest.writeString(this.position);
        dest.writeParcelable(this.license, flags);
        dest.writeTypedList(this.subjectList);
        dest.writeTypedList(this.sectionList);
    }

    public Employee() {
    }

    protected Employee(Parcel in) {
        this.employeeId = in.readInt();
        this.name = in.readString();
        this.age = in.readInt();
        this.address = in.readString();
        this.position = in.readString();
        this.license = in.readParcelable(License.class.getClassLoader());
        this.subjectList = new RealmList<>();
        this.sectionList = new RealmList<>();
        this.subjectList.addAll(in.createTypedArrayList(Subject.CREATOR));
        this.sectionList.addAll(in.createTypedArrayList(Section.CREATOR));
    }

    public static final Parcelable.Creator<Employee> CREATOR = new Parcelable.Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel source) {
            return new Employee(source);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };
}
