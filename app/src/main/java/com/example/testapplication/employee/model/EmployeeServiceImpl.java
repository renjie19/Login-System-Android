package com.example.testapplication.employee.model;


import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmployeeServiceImpl implements  EmployeeService{
    private final String BASE_URL = "http://172.16.0.172:8080";
    private Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    private EmployeeRest resourceHelper = retrofit.create(EmployeeRest.class);

    @Override
    public Call<List<Employee>> getAllEmployees(){
        return resourceHelper.getEmployees();
    }

    @Override
    public Call<Employee> save(Employee employee) {
         return resourceHelper.save(employee);
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
