package com.example.testapplication.employee.model;


import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmployeeService {
    private final String BASE_URL = "http://172.16.0.172:8080";
    private Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    private EmployeeRest resourceHelper = retrofit.create(EmployeeRest.class);

    private Employee result;

    public EmployeeService() {
        this.result = new Employee();
    }

    public Call<List<Employee>> getAllEmployees(){
        return resourceHelper.getEmployees();
    }

    public Employee save(Employee employee) {
        Call<Employee> save = resourceHelper.save(employee);
        save.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if(!response.isSuccessful()){
                    Log.d("error", "onResponse: "+response.code());
            }
                result = response.body();
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Log.d("Failed", "onFailure: "+t.getMessage());
            }
        });
         return result;
    }
}
