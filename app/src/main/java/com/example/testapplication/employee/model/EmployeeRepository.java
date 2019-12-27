package com.example.testapplication.employee.model;

import java.util.List;

public interface EmployeeRepository {
    void save(Employee employee);
    List<Employee> getAll();

}
