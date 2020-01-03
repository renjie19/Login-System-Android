package com.example.testapplication.employee.presenter.edit;

import com.example.testapplication.employee.logic.EmployeeLogic;
import com.example.testapplication.employee.model.Employee;
import com.example.testapplication.employee.views.edit.EditEmployeeView;
import com.example.testapplication.report.logic.ReportLogic;
import com.example.testapplication.report.model.Report;
import com.example.testapplication.utils.CallBack;

import java.util.List;

public class EditPresenterImpl implements EditPresenter {

    private EmployeeLogic employeeLogic = EmployeeLogic.getINSTANCE();
    private ReportLogic reportLogic = ReportLogic.getINSTANCE();
    private EditEmployeeView view;

    public EditPresenterImpl(EditEmployeeView view) {
        this.view = view;
    }

    @Override
    public void update(Employee employee) {
        employeeLogic.update(employee, new CallBack() {
            @Override
            public void onSuccess(Object object) {
                view.showMessage("Success");
                view.enableUpdateButton();
            }

            @Override
            public void onFailure(String message) {
                view.showMessage(message);
            }
        });
    }

    @Override
    public void getReports(int id, String start, String end) {
        reportLogic.getReports(id, start, end, new CallBack() {
            @Override
            public void onSuccess(Object object) {
                List<Report> list = (List<Report>) object;
                if(list == null) {
                    view.showMessage("No Records Found");
                } else {
                    view.updateList(list);
                }
            }

            @Override
            public void onFailure(String message) {
                view.showMessage(message);
            }
        });
    }
}
