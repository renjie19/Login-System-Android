package com.example.testapplication.employee.model;


import com.example.testapplication.employee.EmployeeCallBack;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmployeeServiceImpl implements  EmployeeService{
    private final String BASE_URL = "http://172.16.0.172:8080";
    private Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    private EmployeeRest resourceHelper = retrofit.create(EmployeeRest.class);
    private EmployeeRepository repository = new EmployeeRepositoryImpl();
    private EmployeeCallBack callBack;

    public EmployeeServiceImpl(EmployeeCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public Call<List<Employee>> getAllEmployees(){
        return resourceHelper.getEmployees();
    }

    @Override
    public Employee save(Employee employee) {
        try {
            Employee result = resourceHelper.save(employee).execute().body();
            repository.save(employee);
            callBack.onSuccess(result);
            return result;
        } catch (IOException e) {
            callBack.onFailure(e.getMessage());
        }
        return null;
    }

    @Override
    public Call delete(int id) {
        return resourceHelper.delete(id);
    }

    @Override
    public Call<Employee> update(Employee employee) {
        return resourceHelper.update(employee);
    }
}
