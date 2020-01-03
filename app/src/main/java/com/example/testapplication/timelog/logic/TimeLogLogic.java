package com.example.testapplication.timelog.logic;

import com.example.testapplication.utils.CallBack;

public abstract class TimeLogLogic {
    private static TimeLogLogic INSTANCE;

    public static TimeLogLogic getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new TimeLogLogicImpl();
        }
        return INSTANCE;
    }

    public abstract void login(int id, CallBack callBack);
}
