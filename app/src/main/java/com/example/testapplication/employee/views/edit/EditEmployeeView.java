package com.example.testapplication.employee.views.edit;

import com.example.testapplication.report.model.Report;

import java.util.List;

public interface EditEmployeeView {
    void showMessage(String message);
    void enableUpdateButton();
    void updateList(List<Report> list);
}
