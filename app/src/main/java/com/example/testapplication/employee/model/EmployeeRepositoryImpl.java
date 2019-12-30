package com.example.testapplication.employee.model;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private Realm realm = Realm.getDefaultInstance();

    @Override
    public void save(final Employee employee) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insert(employee);
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

    @Override
    public void saveAll(final List<Employee> list) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmList<Employee> realmList = new RealmList<>();
                realmList.addAll(list);
                realm.insertOrUpdate(realmList);
            }
        });
    }

    @Override
    public void delete(final int id) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Employee employee = realm.where(Employee.class).equalTo("employeeId", id).findFirst();
                if(employee != null) {
                    employee.deleteFromRealm();
                }
            }
        });
    }
}
