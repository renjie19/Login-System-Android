package com.example.testapplication.report.logic;

import com.example.testapplication.report.model.Report;
import com.example.testapplication.utils.CallBack;

import java.util.List;

public abstract class ReportLogic {

    private static ReportLogic INSTANCE;

    public static ReportLogic getINSTANCE() {
        if(INSTANCE == null) {
            INSTANCE = new ReportLogicImpl();
        }
        return INSTANCE;
    }

    public abstract List<Report> getReports(int id, String start, String end, CallBack callBack);
}
