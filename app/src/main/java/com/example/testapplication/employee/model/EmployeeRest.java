package com.example.testapplication.employee.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EmployeeRest {
    @GET("api/employee/getAll")
    Call<List<Employee>> getEmployees();

    @POST("api/employee/save")
    Call<Employee> save(@Body Employee employee);
}
