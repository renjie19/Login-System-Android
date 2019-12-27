package com.example.testapplication.timelog.presenter;

import com.example.testapplication.timelog.TimeLogCallBack;
import com.example.testapplication.timelog.model.TimeLogService;
import com.example.testapplication.timelog.model.TimeLogServiceImpl;


public class TimeLogPresenter {

    private TimeLogService service;

    public TimeLogPresenter(TimeLogCallBack callBack) {
        this.service = new TimeLogServiceImpl(callBack);
    }

    public void login(int id) {
        service.login(id);
    }
}
