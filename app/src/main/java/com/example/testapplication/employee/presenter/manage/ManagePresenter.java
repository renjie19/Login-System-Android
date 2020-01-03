package com.example.testapplication.employee.presenter.manage;

import com.example.testapplication.employee.model.Employee;

public interface ManagePresenter {
    void getAll();

    void delete(Employee employee);
}
