package com.example.testapplication.employee.logic;

import com.example.testapplication.employee.model.Employee;
import com.example.testapplication.utils.CallBack;

public abstract class EmployeeLogic {
    private static EmployeeLogic INSTANCE;

    public static EmployeeLogic getINSTANCE() {
        if(INSTANCE == null) {
            INSTANCE = new EmployeeLogicImpl();
        }
        return INSTANCE;
    }

    public abstract void update(Employee employee, CallBack callBack);

    public abstract void save(Employee employee, CallBack callBack);

    public abstract void getAll(CallBack callBack);

    public abstract  void delete(Employee employee, CallBack callBack);

    public abstract void sync();
}
