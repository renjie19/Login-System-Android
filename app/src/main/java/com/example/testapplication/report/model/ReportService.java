package com.example.testapplication.report.model;


import java.util.List;

import retrofit2.Call;

public interface ReportService {
    Call<List<Report>> getReport(int id,String startDate,String endDate);
}
