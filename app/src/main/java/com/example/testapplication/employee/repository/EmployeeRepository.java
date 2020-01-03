package com.example.testapplication.employee.repository;

import com.example.testapplication.employee.model.Employee;
import com.example.testapplication.utils.CallBack;

import java.util.List;

public interface EmployeeRepository {
    void save(Employee employee, CallBack callBack);
    void getAll(CallBack callBack);
    void saveAll(List<Employee> list, CallBack callBack);
    void delete(int id, CallBack callBack);
}
