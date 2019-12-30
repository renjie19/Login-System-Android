package com.example.testapplication.report.presenter;

import com.example.testapplication.report.ReportCallBack;
import com.example.testapplication.report.model.ReportService;
import com.example.testapplication.report.model.ReportServiceImpl;

public class ReportPresenter {
    private ReportService service;

    public ReportPresenter(ReportCallBack callBack) {
        this.service = new ReportServiceImpl(callBack);
    }
}
