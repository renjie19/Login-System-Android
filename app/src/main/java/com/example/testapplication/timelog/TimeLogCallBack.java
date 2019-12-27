package com.example.testapplication.timelog;

import com.example.testapplication.employee.EmployeeCallBack;
import com.example.testapplication.timelog.model.TimeLog;

public interface TimeLogCallBack {
    void onSuccess(TimeLog timeLog);
    void onFailure(String message);
}
