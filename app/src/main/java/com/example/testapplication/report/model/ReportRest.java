package com.example.testapplication.report.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ReportRest {
    @GET("api/report/viewByEmployeeId")
    Call<List<Report>> getReport(@Query("id") int id, @Query("startDate") String startDate, @Query("endDate") String endDate);
}
