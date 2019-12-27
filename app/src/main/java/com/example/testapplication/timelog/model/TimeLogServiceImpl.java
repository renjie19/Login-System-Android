package com.example.testapplication.timelog.model;

import com.example.testapplication.timelog.TimeLogCallBack;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TimeLogServiceImpl implements TimeLogService {

    private Retrofit retrofit = new Retrofit.Builder().baseUrl("http://172.16.0.172:8080").addConverterFactory(GsonConverterFactory.create()).build();
    private TimelogRest timelogRest = retrofit.create(TimelogRest.class);
    private TimeLogCallBack callBack;

    public TimeLogServiceImpl(TimeLogCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void login(int id) {
        timelogRest.login(id).enqueue(new Callback<TimeLog>() {
            @Override
            public void onResponse(Call<TimeLog> call, Response<TimeLog> response) {
                if(!response.isSuccessful()) {
                    callBack.onFailure(response.message());
                } else {
                    callBack.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<TimeLog> call, Throwable t) {
                callBack.onFailure(t.getMessage());
            }
        });
    }
}
