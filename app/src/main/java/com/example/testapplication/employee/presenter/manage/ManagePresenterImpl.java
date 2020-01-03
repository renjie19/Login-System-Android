package com.example.testapplication.employee.presenter.manage;

import com.example.testapplication.employee.logic.EmployeeLogic;
import com.example.testapplication.employee.model.Employee;
import com.example.testapplication.employee.views.manage.ManageEmployeeView;
import com.example.testapplication.utils.CallBack;

import java.util.ArrayList;
import java.util.List;

public class ManagePresenterImpl implements ManagePresenter {
    private EmployeeLogic logic = EmployeeLogic.getINSTANCE();
    private ManageEmployeeView view;

    public ManagePresenterImpl(ManageEmployeeView view) {
        this.view = view;
    }

    @Override
    public void getAll() {
        logic.getAll(new CallBack() {
            @Override
            public void onSuccess(Object object) {
                List<Employee> employees = (List<Employee>) object;
                if(employees == null) {
                    view.updateList(new ArrayList<>());
                } else {
                    view.updateList(employees);
                }
            }

            @Override
            public void onFailure(String message) {
                view.showMessage(message);
            }
        });
    }

    @Override
    public void delete(Employee employee) {
        logic.delete(employee, new CallBack() {
            @Override
            public void onSuccess(Object object) {
                view.showMessage("Success");
                view.refreshList();
            }

            @Override
            public void onFailure(String message) {
                view.showMessage(message);
            }
        });
    }
}
