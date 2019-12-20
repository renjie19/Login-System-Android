package com.example.testapplication.employee.presenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.testapplication.R;
import com.example.testapplication.employee.model.Employee;
import com.example.testapplication.employee.adapter.ManageEmployeeAdapter;
import com.example.testapplication.employee.model.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageEmployee extends AppCompatActivity implements ManageEmployeeAdapter.ItemClick {

    private List<Employee> employees = new ArrayList<>();
    private EmployeeServiceImpl resource = new EmployeeServiceImpl();

    @Override
    protected void onResume() {
        super.onResume();

        resource.getAllEmployees().enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                employees = response.body();
                setRecyclerView();
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d("GetEmployees Failed -> ", t.getMessage());
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_manage);

        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent create = new Intent(getApplicationContext(), CreateEmployee.class);
                startActivity(create);
            }
        });
    }

    @Override
    public void itemClick(int position) {
        Employee employee = employees.get(position);
        Intent editActivity = new Intent(getApplicationContext(),EditDetails.class);
        editActivity.putExtra("data",employee);
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
                        resource.delete(employee.getEmployeeId()).enqueue(new Callback() {
                            @Override
                            public void onResponse(Call call, Response response) {
                                finish();
                                startActivity(getIntent());
                                Toast.makeText(getApplicationContext(),"Process Complete", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {
                                Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                })
                .setNegativeButton("NO", null )
                .show();
    }

    private void setRecyclerView(){
        RecyclerView rv = findViewById(R.id.employee_rv);
        ManageEmployeeAdapter manageEmployeeAdapter = new ManageEmployeeAdapter(employees, this);
        rv.setAdapter(manageEmployeeAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}
