package com.example.testapplication.employee.views.manage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.testapplication.R;
import com.example.testapplication.employee.model.Employee;
import com.example.testapplication.employee.adapter.ManageEmployeeAdapter;
import com.example.testapplication.employee.presenter.manage.ManagePresenter;
import com.example.testapplication.employee.presenter.manage.ManagePresenterImpl;
import com.example.testapplication.employee.views.create.CreateEmployeeActivity;
import com.example.testapplication.employee.views.edit.EditDetailsActivity;

import java.util.List;

public class ManageEmployeeActivity extends AppCompatActivity implements ManageEmployeeAdapter.ItemClick,ManageEmployeeView {

    private List<Employee> employees;
    private ManagePresenter presenter;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_manage);
        this.rv = findViewById(R.id.employee_rv);
        presenter = new ManagePresenterImpl(this);

        findViewById(R.id.floatingActionButton).setOnClickListener(v -> {
            Intent create = new Intent(getApplicationContext(), CreateEmployeeActivity.class);
            startActivity(create);
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
        Intent editActivity = new Intent(getApplicationContext(), EditDetailsActivity.class);
        editActivity.putExtra("data", employee);
        startActivity(editActivity);
    }

    @Override
    public void longClickAction(int position) {
        final Employee employee = employees.get(position);
        new AlertDialog.Builder(this)
                .setTitle("Delete Employee ?")
                .setMessage("Are you sure to remove "+employee.getName()+" ?")
                .setPositiveButton("YES", (dialog, which) -> presenter.delete(employee))
                .setNegativeButton("NO", null )
                .show();
    }

    private void loadEmployees(){
        presenter.getAll();
    }

    @Override
    public void updateList(List<Employee> list) {
        employees = list;
        ManageEmployeeAdapter manageEmployeeAdapter = new ManageEmployeeAdapter(employees, this);
        rv.setAdapter(manageEmployeeAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showMessage(String message) {
        runOnUiThread(() -> {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        });

    }

    @Override
    public void refreshList() {
        loadEmployees();
    }
}
