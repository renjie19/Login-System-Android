package com.example.testapplication.employee.service;


import com.example.testapplication.employee.model.Employee;
import com.example.testapplication.utils.CallBack;
import com.example.testapplication.utils.Preferences;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmployeeServiceImpl implements EmployeeService {
    private final String BASE_URL = "http://" + Preferences.getAddress() + ":8080";
    private Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    private EmployeeRest resourceHelper = retrofit.create(EmployeeRest.class);


    @Override
    public void save(Employee employee, CallBack callBack) {
        resourceHelper.save(employee).enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if(!response.isSuccessful()) {
                    callBack.onFailure(response.message());
                } else {
                    callBack.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {

            }
        });
    }

    @Override
    public void syncData(CallBack callBack) {
        resourceHelper.getEmployees().enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if(!response.isSuccessful()) {
                    callBack.onFailure(response.message());
                } else {
                    callBack.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                callBack.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void delete(final int id, CallBack callBack) {
        resourceHelper.delete(id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    callBack.onFailure(response.message());
                } else {
                    callBack.onSuccess(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                    callBack.onFailure(t.getMessage());
            }
        });
//        new Thread(() -> {
//            try {
//                resourceHelper.delete(id).execute();
//            } catch (Exception e) {
//                Log.d("Deletion", "Error Occurred " + e.getMessage());
//            }
//        }).start();
//        repository.delete(id);
    }

    @Override
    public void update(final Employee employee, CallBack callBack) {
        resourceHelper.update(employee).enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if(!response.isSuccessful()) {
                    callBack.onFailure(response.message());
                } else {
                    callBack.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {

            }
        });
    }
}
