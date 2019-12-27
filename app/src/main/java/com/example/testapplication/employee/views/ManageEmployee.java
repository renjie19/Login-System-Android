package com.example.testapplication.employee.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.testapplication.R;
import com.example.testapplication.employee.EmployeeCallBack;
import com.example.testapplication.employee.model.Employee;
import com.example.testapplication.employee.adapter.ManageEmployeeAdapter;
import com.example.testapplication.employee.model.EmployeeService;
import com.example.testapplication.employee.presenter.EmployeePresenter;
import com.example.testapplication.employee.model.EmployeeServiceImpl;

import java.util.List;

public class ManageEmployee extends AppCompatActivity implements ManageEmployeeAdapter.ItemClick, EmployeeCallBack {

    private List<Employee> employees;
    private EmployeePresenter presenter = new EmployeePresenter(this);
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_manage);
        this.rv = findViewById(R.id.employee_rv);

        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent create = new Intent(getApplicationContext(), CreateEmployee.class);
                startActivity(create);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadEmployees();
    }

    @Override
    public void itemClick(int position) {
        Employee employee = employees.get(position);
        Intent editActivity = new Intent(getApplicationContext(),EditDetails.class);
        editActivity.putExtra("data", employee);
        startActivity(editActivity);
    }

    @Override
    public void longClickAction(int position) {
        final Employee employee = employees.get(position);
        new AlertDialog.Builder(this)
                .setTitle("Delete Employee ?")
                .setMessage("Are you sure to remove "+employee.getName()+" ?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.delete(employee);
                    }
                })
                .setNegativeButton("NO", null )
                .show();
    }

    @Override
    public void onSuccess(Employee employee) {
        Toast positive = Toast.makeText(getApplicationContext(),"Complete",Toast.LENGTH_LONG);
        positive.getView().setBackgroundColor(Color.GREEN);
        positive.show();
        loadEmployees();
    }

    @Override
    public void onFailure(final String message) {
        Toast negative = Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG);
        negative.getView().setBackgroundColor(Color.RED);
        negative.show();
    }

    private void loadEmployees(){
        this.employees = presenter.getAll();
        ManageEmployeeAdapter manageEmployeeAdapter = new ManageEmployeeAdapter(employees, this);
        rv.setAdapter(manageEmployeeAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}
