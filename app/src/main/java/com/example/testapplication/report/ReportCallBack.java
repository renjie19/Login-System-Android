package com.example.testapplication.report;

import com.example.testapplication.report.model.Report;

import java.util.List;

public interface ReportCallBack {
    void onSuccess(List<Report> list);
    void onFailure(String message);
}
