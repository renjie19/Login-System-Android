package com.example.testapplication.employee.model;

import java.util.List;

import retrofit2.Call;

public interface EmployeeService {
    Call<List<Employee>> getAllEmployees();
    Call<Employee> save(Employee employee);
    Call delete(int id);
    Call<Employee> update(Employee employee);
}
