package com.example.testapplication.employee.model;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

public interface EmployeeService {
    Call<List<Employee>> getAllEmployees();
    void save(Employee employee);
    void syncData();
    void delete(int id) throws IOException;
    Call<Employee> update(Employee employee);
}
