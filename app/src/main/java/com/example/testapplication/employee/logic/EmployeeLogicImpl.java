package com.example.testapplication.employee.logic;

import com.example.testapplication.employee.model.Employee;
import com.example.testapplication.employee.repository.EmployeeRepository;
import com.example.testapplication.employee.repository.EmployeeRepositoryImpl;
import com.example.testapplication.employee.service.EmployeeService;
import com.example.testapplication.employee.service.EmployeeServiceImpl;
import com.example.testapplication.utils.CallBack;

import java.util.List;

class EmployeeLogicImpl extends EmployeeLogic {
    private EmployeeRepository repository;
    private EmployeeService service;

    protected EmployeeLogicImpl() {
        repository = new EmployeeRepositoryImpl();
        service = new EmployeeServiceImpl();
    }


    @Override
    public void update(Employee employee, CallBack callBack) {
        service.update(employee, new CallBack() {
            @Override
            public void onSuccess(Object object) {
                Employee employee = (Employee) object;
                if(null != employee) {
                    repository.save(employee,callBack);
                }
            }

            @Override
            public void onFailure(String message) {
                callBack.onFailure(message);
            }
        });
    }

    @Override
    public void save(Employee employee, CallBack callBack) {
        service.save(employee, new CallBack() {
            @Override
            public void onSuccess(Object object) {
                Employee employee = (Employee) object;
                if(null != employee) {
                    repository.save(employee, callBack);
                }
            }

            @Override
            public void onFailure(String message) {
                callBack.onFailure(message);
            }
        });
    }

     @Override
     public void getAll(CallBack callBack) {
         repository.getAll(new CallBack() {
             @Override
             public void onSuccess(Object object) {
                 callBack.onSuccess(object);
             }

             @Override
             public void onFailure(String message) {
                callBack.onFailure(message);
             }
         });
     }

     @Override
     public void delete(Employee employee, CallBack callBack) {
         service.delete(employee.getEmployeeId(), new CallBack() {
             @Override
             public void onSuccess(Object object) {
                 repository.delete(employee.getEmployeeId(), callBack);
             }

             @Override
             public void onFailure(String message) {
                callBack.onFailure(message);
             }
         });
     }

     @Override
     public void sync() {
         service.syncData(new CallBack() {
             @Override
             public void onSuccess(Object object) {
                 List<Employee> employees = (List<Employee>) object;
                 if(employees != null) {
                     repository.saveAll(employees, null);
                 }
             }

             @Override
             public void onFailure(String message) {

             }
         });
     }
 }
