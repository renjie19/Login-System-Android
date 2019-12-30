package com.example.testapplication.report.model;

import com.example.testapplication.report.ReportCallBack;
import com.example.testapplication.utils.Server;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReportServiceImpl implements ReportService {
    private final String BASE_URL = "http://"+ Server.getConfig().getServerAddress()+":8080";
    private Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    private ReportRest report = retrofit.create(ReportRest.class);
    private ReportCallBack callBack;

    public ReportServiceImpl(ReportCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void getReport(int id, String startDate, String endDate) {
        report.getReport(id, startDate, endDate).enqueue(new Callback<List<Report>>() {
            @Override
            public void onResponse(Call<List<Report>> call, Response<List<Report>> response) {
                callBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Report>> call, Throwable t) {
                callBack.onFailure(t.getMessage());
            }
        });
    }
}
