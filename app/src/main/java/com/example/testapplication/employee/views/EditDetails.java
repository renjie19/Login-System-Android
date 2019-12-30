package com.example.testapplication.employee.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.testapplication.employee.EmployeeCallBack;
import com.example.testapplication.employee.presenter.EmployeePresenter;
import com.example.testapplication.report.ReportCallBack;
import com.example.testapplication.report.views.ManageReports;
import com.example.testapplication.R;
import com.example.testapplication.employee.model.Employee;
import com.example.testapplication.report.model.Report;
import com.example.testapplication.report.model.ReportService;
import com.example.testapplication.report.model.ReportServiceImpl;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditDetails extends AppCompatActivity implements EmployeeCallBack, ReportCallBack {

    private EditText edit_nameField;
    private EditText edit_ageField;
    private EditText edit_addressField;
    private EditText edit_licenseField;
    private EditText edit_positionField;
    private EditText edit_employeeId;
    private ImageButton subject_button;
    private ImageButton section_button;
    private ImageButton report_button;
    private Context context;
    private DatePicker startDate;
    private DatePicker endDate;
    private Employee employee;
    private Button updateButton;
    private EmployeePresenter employeePresenter;
    private ReportService reportService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_edit_details);
        initializeFields();
        employeePresenter = new EmployeePresenter(this);
        reportService = new ReportServiceImpl(this);

        this.employee = getIntent().getParcelableExtra("data");

        subject_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        section_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        report_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view = LayoutInflater.from(context).inflate(R.layout.report_date_select, null);
                startDate = view.findViewById(R.id.startDatePicker);
                endDate = view.findViewById(R.id.endDatePicker);
                builder.setView(view);
                builder.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String start = startDate.getYear() + "-" + (startDate.getMonth() + 1) + "-" + startDate.getDayOfMonth();
                        String end = endDate.getYear() + "-" + (endDate.getMonth() + 1) + "-" + endDate.getDayOfMonth();
                        reportService.getReport(employee.getEmployeeId(), start, end);
                    }
                });
                builder.setNegativeButton("CANCEL", null);
                builder.show();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateButton.setEnabled(false);
                employee.setName(edit_nameField.getText().toString());
                employee.setAge(Integer.parseInt(edit_ageField.getText().toString()));
                employee.setAddress(String.valueOf(edit_addressField.getText()));
                employee.setPosition(String.valueOf(edit_positionField.getText()));
                employee.getLicense().setLicenseNumber(Integer.parseInt(edit_licenseField.getText().toString()));
                employeePresenter.update(employee);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (employee != null) {
            edit_employeeId.setText(String.valueOf(employee.getEmployeeId()));
            edit_nameField.setText(employee.getName());
            edit_ageField.setText(String.valueOf(employee.getAge()));
            edit_addressField.setText(employee.getAddress());
            edit_positionField.setText(employee.getPosition());
            edit_licenseField.setText(String.valueOf(employee.getLicense().getLicenseNumber()));
        }
    }

    private void initializeFields() {
        this.context = this;
        this.edit_employeeId = findViewById(R.id.edit_employeeId);
        this.edit_nameField = findViewById(R.id.edit_nameField);
        this.edit_ageField = findViewById(R.id.edit_ageField);
        this.edit_addressField = findViewById(R.id.edit_addressField);
        this.edit_positionField = findViewById(R.id.edit_positionField);
        this.edit_licenseField = findViewById(R.id.edit_licenseField);
        this.subject_button = findViewById(R.id.subjectButton);
        this.section_button = findViewById(R.id.sectionsButton);
        this.report_button = findViewById(R.id.reportsButton);
        this.updateButton = findViewById(R.id.edit_updateButton);
    }

    @Override
    public void onSuccess(Employee employee) {
        Toast.makeText(this,"Complete", Toast.LENGTH_SHORT).show();
        updateButton.setEnabled(true);
    }

    @Override
    public void onSuccess(List<Report> list) {
        ArrayList<Report> reports = ((ArrayList<Report>) list);
        Intent manageReports = new Intent(getApplicationContext(), ManageReports.class);
        manageReports.putParcelableArrayListExtra("report", reports);
        startActivity(manageReports);
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}
