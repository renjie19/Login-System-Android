package com.example.testapplication.timelog.model;
import com.example.testapplication.utils.CallBack;
import com.example.testapplication.utils.Preferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TimeLogServiceImpl implements TimeLogService {

    private TimelogRest timelogRest;

    public TimeLogServiceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://" + Preferences.getAddress() + ":8080")
                .addConverterFactory(GsonConverterFactory.create()).build();
        timelogRest = retrofit.create(TimelogRest.class);
    }

    @Override
    public void login(int id, CallBack callBack) {
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
