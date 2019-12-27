package com.example.testapplication.employee.model;

import android.util.Log;

import java.util.List;

import io.realm.Realm;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    Realm realm = Realm.getDefaultInstance();

    @Override
    public void save(final Employee employee) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insert(employee);
//                Employee realmEmployee = realm.createObject(Employee.class);
//                realmEmployee.setEmployeeId(employee.getEmployeeId());
//                realmEmployee.setName(employee.getName());
//                realmEmployee.setAge(employee.getAge());
//                realmEmployee.setAddress(employee.getAddress());
//                realmEmployee.setPosition(employee.getPosition());
//                realmEmployee.setLicense(employee.getLicense());
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d("Realm", "onSuccess: Added to DB");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.d("Realm", "onError: " + error.getMessage());
            }
        });
    }

    @Override
    public List<Employee> getAll() {
        return realm.where(Employee.class).findAll();
    }
}
