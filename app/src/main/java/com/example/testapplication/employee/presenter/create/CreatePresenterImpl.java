package com.example.testapplication.employee.presenter.create;

import com.example.testapplication.employee.model.Employee;
import com.example.testapplication.employee.logic.EmployeeLogic;
import com.example.testapplication.employee.views.create.CreateEmployeeView;
import com.example.testapplication.utils.CallBack;

public class CreatePresenterImpl implements CreatePresenter {
    private EmployeeLogic logic = EmployeeLogic.getINSTANCE();
    private CreateEmployeeView view;

    public CreatePresenterImpl(CreateEmployeeView view) {
        this.view = view;
    }

    @Override
    public void save(Employee employee) {
        logic.save(employee, new CallBack() {
            @Override
            public void onSuccess(Object object) {
                view.showMessage("Success");
                view.clearFields();
            }

            @Override
            public void onFailure(String message) {
                view.showMessage(message);
            }
        });
    }
}
