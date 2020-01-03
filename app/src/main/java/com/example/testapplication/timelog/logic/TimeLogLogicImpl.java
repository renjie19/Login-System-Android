package com.example.testapplication.timelog.logic;

import com.example.testapplication.timelog.model.TimeLog;
import com.example.testapplication.timelog.model.TimeLogService;
import com.example.testapplication.timelog.model.TimeLogServiceImpl;
import com.example.testapplication.utils.CallBack;

class TimeLogLogicImpl extends TimeLogLogic {
    private TimeLogService service;

    protected TimeLogLogicImpl(){
        service = new TimeLogServiceImpl();
    }

    @Override
    public void login(int id, CallBack callBack) {
        service.login(id, new CallBack() {
            @Override
            public void onSuccess(Object object) {
                TimeLog timeLog = (TimeLog) object;
                if(timeLog != null) {
                    callBack.onSuccess(timeLog);
                } else {
                    callBack.onFailure("Error Occurred");
                }
            }

            @Override
            public void onFailure(String message) {
                callBack.onFailure(message);
            }
        });
    }
}
