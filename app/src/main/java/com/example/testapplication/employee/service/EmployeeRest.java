package com.example.testapplication.employee.service;

import com.example.testapplication.employee.model.Employee;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface EmployeeRest {
    @GET("api/employee/getAll")
    Call<List<Employee>> getEmployees();

    @POST("api/employee/save")
    Call<Employee> save(@Body Employee employee);

    @DELETE("api/employee/deleteEmployee")
    Call<ResponseBody> delete(@Query("id") int id);

    @POST("api/employee/update")
    Call<Employee> update(@Body Employee employee);
}
