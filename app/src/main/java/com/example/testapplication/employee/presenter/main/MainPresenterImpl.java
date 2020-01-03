package com.example.testapplication.employee.presenter.main;

import com.example.testapplication.employee.logic.EmployeeLogic;
import com.example.testapplication.employee.views.main.MainView;
import com.example.testapplication.timelog.logic.TimeLogLogic;
import com.example.testapplication.timelog.model.TimeLog;
import com.example.testapplication.utils.CallBack;

import java.util.Date;

public class MainPresenterImpl implements MainPresenter {
    private EmployeeLogic logic = EmployeeLogic.getINSTANCE();
    private TimeLogLogic timeLogLogic = TimeLogLogic.getInstance();
    private MainView view;

    public MainPresenterImpl(MainView view) {
        this.view = view;
    }

    @Override
    public void sync() {
        logic.sync();
    }

    @Override
    public void login(int id) {
        timeLogLogic.login(id, new CallBack() {
            @Override
            public void onSuccess(Object object) {
                TimeLog log = (TimeLog) object;
                if(log != null) {
                    view.showMessage("Login Successful\n" + new Date(log.getTime()));
                    view.clearFields();
                } else {
                    onFailure("Error Occurred");
                }
            }

            @Override
            public void onFailure(String message) {
                view.showMessage(message);
            }
        });
    }
}
