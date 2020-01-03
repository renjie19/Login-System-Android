package com.example.testapplication.report.model;
import com.example.testapplication.utils.CallBack;
import com.example.testapplication.utils.Preferences;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReportServiceImpl implements ReportService {
    private final String BASE_URL = "http://" + Preferences.getAddress() + ":8080";
    private Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    private ReportRest report = retrofit.create(ReportRest.class);

    @Override
    public void getReport(int id, String startDate, String endDate, CallBack callBack) {
        report.getReport(id, startDate, endDate).enqueue(new Callback<List<Report>>() {
            @Override
            public void onResponse(Call<List<Report>> call, Response<List<Report>> response) {
                if(!response.isSuccessful()) {
                    callBack.onFailure(response.message());
                } else {
                    callBack.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Report>> call, Throwable t) {
                callBack.onFailure(t.getMessage());
            }
        });
    }
}
