package com.example.testapplication.employee.presenter;

import com.example.testapplication.employee.EmployeeCallBack;
import com.example.testapplication.employee.model.Employee;
import com.example.testapplication.employee.model.EmployeeRepository;
import com.example.testapplication.employee.model.EmployeeRepositoryImpl;
import com.example.testapplication.employee.model.EmployeeService;
import com.example.testapplication.employee.model.EmployeeServiceImpl;

import java.util.List;

public class EmployeePresenter {
    private EmployeeService service;
    private EmployeeRepository repository;
    private EmployeeCallBack callBack;

    public EmployeePresenter(EmployeeCallBack callBack) {
        service = new EmployeeServiceImpl(callBack);
        repository = new EmployeeRepositoryImpl();
    }

    public void save(final Employee employee){
        new Thread(new Runnable() {
            @Override
            public void run() {
                service.save(employee);
                callBack.onSuccess(employee);
            }
        }).start();
    }

    public List<Employee> getAll() {
        return repository.getAll();
    }

    public void sync() {
        service.syncData();
    }
}
