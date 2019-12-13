package com.example.testapplication.employee.presenter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.testapplication.license.License;
import com.example.testapplication.R;
import com.example.testapplication.employee.model.Employee;
import com.example.testapplication.employee.model.EmployeeService;

public class CreateEmployee extends AppCompatActivity {

    private Employee employee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        final EditText nameField = findViewById(R.id.nameField);
        final EditText ageField = findViewById(R.id.ageField);
        final EditText addressField = findViewById(R.id.addressField);
        final EditText positionField = findViewById(R.id.positionField);
        final EditText licenseField = findViewById(R.id.licenseField);

        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                License license = new License();
                license.setLicenseNumber(Integer.parseInt(licenseField.getText().toString()));
                Employee employee = new Employee();
                employee.setName(nameField.getText().toString());
                employee.setAge(Integer.parseInt(ageField.getText().toString()));
                employee.setAddress(addressField.getText().toString());
                employee.setPosition(positionField.getText().toString());
                employee.setLicense(license);

                EmployeeService employeeService = new EmployeeService();
                employeeService.save(employee);

                nameField.setText(null);
                ageField.setText(null);
                addressField.setText(null);
                positionField.setText(null);
                licenseField.setText(null);
            }
        });





    }
}
