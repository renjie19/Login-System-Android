package com.example.testapplication.report.model;


import com.example.testapplication.utils.CallBack;

public interface ReportService {
    void getReport(int id, String startDate, String endDate, CallBack callBack);
}
