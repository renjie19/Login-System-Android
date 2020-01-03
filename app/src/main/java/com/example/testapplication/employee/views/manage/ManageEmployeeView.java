package com.example.testapplication.employee.views.manage;

import com.example.testapplication.employee.model.Employee;

import java.util.List;

public interface ManageEmployeeView {
    void updateList(List<Employee> list);

    void showMessage(String message);

    void refreshList();
}
