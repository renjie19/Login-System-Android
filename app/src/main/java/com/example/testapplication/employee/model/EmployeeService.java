package com.example.testapplication.employee.model;

import com.example.testapplication.employee.EmployeeCallBack;

import java.util.List;

import retrofit2.Call;

public interface EmployeeService {
    Call<List<Employee>> getAllEmployees();
    Employee save(Employee employee);
    Call delete(int id);
    Call<Employee> update(Employee employee);
}
