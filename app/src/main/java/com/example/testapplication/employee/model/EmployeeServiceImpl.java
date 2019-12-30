package com.example.testapplication.employee.model;


import android.util.Log;

import com.example.testapplication.employee.EmployeeCallBack;
import com.example.testapplication.utils.Server;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmployeeServiceImpl implements EmployeeService {
    private final String BASE_URL = "http://" + Server.getConfig().getServerAddress() + ":8080";
    private Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    private EmployeeRest resourceHelper = retrofit.create(EmployeeRest.class);
    private EmployeeRepository repository = new EmployeeRepositoryImpl();
    private EmployeeCallBack callBack;

    public EmployeeServiceImpl(EmployeeCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public Call<List<Employee>> getAllEmployees() {
        return resourceHelper.getEmployees();
    }

    @Override
    public void save(Employee employee) {
        try {
            Employee result = resourceHelper.save(employee).execute().body();
            callBack.onSuccess(result);
            syncData();
        } catch (IOException e) {
            callBack.onFailure(e.getMessage());
        }
    }

    @Override
    public void syncData() {
        resourceHelper.getEmployees().enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                repository.saveAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d("Failed", "onFailure: Fetching list failed!!!!");
            }
        });
    }

    @Override
    public void delete(final int id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    resourceHelper.delete(id).execute();
                } catch (IOException e) {
                    Log.d("Deletion", "Error Occurred " + e.getMessage());
                }
            }
        }).start();
        repository.delete(id);
    }

    @Override
    public void update(final Employee employee) {
        resourceHelper.update(employee).enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                callBack.onSuccess(employee);
                syncData();
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                callBack.onFailure(t.getMessage());
            }
        });
    }
}
