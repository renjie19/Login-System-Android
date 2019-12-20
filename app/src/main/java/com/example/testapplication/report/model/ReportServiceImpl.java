package com.example.testapplication.report.model;

import com.example.testapplication.employee.model.EmployeeRest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReportServiceImpl implements ReportService {
    private final String BASE_URL = "http://172.16.0.172:8080";
    private Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    private ReportRest report = retrofit.create(ReportRest.class);

    @Override
    public Call<List<Report>> getReport(int id, String startDate, String endDate) {
        return report.getReport(id, startDate, endDate);
    }
}
