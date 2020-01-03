package com.example.testapplication.report.logic;

import com.example.testapplication.report.model.Report;
import com.example.testapplication.report.model.ReportService;
import com.example.testapplication.report.model.ReportServiceImpl;
import com.example.testapplication.utils.CallBack;

import java.util.List;

class ReportLogicImpl extends ReportLogic {
    private ReportService service;

    protected ReportLogicImpl() {
        service = new ReportServiceImpl();
    }

    @Override
    public List<Report> getReports(int id, String start, String end, CallBack callBack) {
        service.getReport(id, start, end, new CallBack() {
            @Override
            public void onSuccess(Object object) {
                List<Report> reports = (List<Report>) object;
                if(reports == null) {
                    callBack.onFailure("");
                } else {
                    callBack.onSuccess(reports);
                }
            }

            @Override
            public void onFailure(String message) {
                callBack.onFailure(message);
            }
        });
        return null;
    }
}
