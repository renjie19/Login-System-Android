package com.example.testapplication.employee.service;

import com.example.testapplication.employee.model.Employee;
import com.example.testapplication.utils.CallBack;


public interface EmployeeService {
    void save(Employee employee, CallBack callBack);
    void syncData(CallBack callBack);
    void delete(int id, CallBack callBack);
    void update(Employee employee, CallBack callBack);
}
