package com.example.testapplication.employee.presenter.edit;

import com.example.testapplication.employee.model.Employee;

public interface EditPresenter {
    void update(Employee employee);

    void getReports(int id, String start, String end);
}
