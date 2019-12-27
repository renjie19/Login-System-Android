package com.example.testapplication.employee;

import com.example.testapplication.employee.model.Employee;

import java.util.List;

public interface EmployeeCallBack {
    void onSuccess(Employee employee);
    void onFailure(String message);
}
