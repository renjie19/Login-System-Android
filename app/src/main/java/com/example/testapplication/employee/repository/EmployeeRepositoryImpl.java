package com.example.testapplication.employee.repository;

import com.example.testapplication.employee.model.Employee;
import com.example.testapplication.utils.CallBack;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private Realm realm = Realm.getDefaultInstance();

    @Override
    public void save(final Employee employee, CallBack callBack) {
        realm.executeTransactionAsync(realm -> realm.insertOrUpdate(employee),
                () -> callBack.onSuccess(null),
                error -> callBack.onFailure("Saving Failed"));
    }

    @Override
    public void getAll(CallBack callBack) {
        callBack.onSuccess(realm.where(Employee.class).findAll());
    }

    @Override
    public void saveAll(final List<Employee> list, CallBack callBack) {
        realm.executeTransaction(realm -> {
            RealmList<Employee> realmList = new RealmList<>();
            realmList.addAll(list);
            realm.insertOrUpdate(realmList);
            //callBack.onSuccess(list);
        });
    }

    @Override
    public void delete(final int id, CallBack callBack) {
        realm.executeTransactionAsync(realm -> {
            Employee employee = realm.where(Employee.class).equalTo("employeeId", id).findFirst();
            if(employee != null) {
                employee.deleteFromRealm();
                callBack.onSuccess(null);
            }
        });
    }
}
