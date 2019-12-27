package com.example.testapplication.employee.views;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testapplication.employee.EmployeeCallBack;
import com.example.testapplication.employee.presenter.EmployeePresenter;
import com.example.testapplication.license.License;
import com.example.testapplication.R;
import com.example.testapplication.employee.model.Employee;

import java.util.List;

public class CreateEmployee extends AppCompatActivity implements EmployeeCallBack {

    private EmployeePresenter presenter;
    private EditText nameField;
    private EditText ageField;
    private EditText addressField;
    private EditText positionField;
    private EditText licenseField;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_create);
        this.presenter = new EmployeePresenter(this);

        this.nameField = findViewById(R.id.nameField);
        this.ageField = findViewById(R.id.ageField);
        this.addressField = findViewById(R.id.addressField);
        this.positionField = findViewById(R.id.positionField);
        this.licenseField = findViewById(R.id.licenseField);

        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                License license = new License();
                license.setLicenseNumber(Integer.parseInt(licenseField.getText().toString()));
                final Employee employee = new Employee();
                employee.setName(nameField.getText().toString());
                employee.setAge(Integer.parseInt(ageField.getText().toString()));
                employee.setAddress(addressField.getText().toString());
                employee.setPosition(positionField.getText().toString());
                employee.setLicense(license);
                presenter.save(employee);
            }
        });
    }

    @Override
    public void onSuccess(Employee employee) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast positive = Toast.makeText(getApplicationContext(),"Success", Toast.LENGTH_SHORT);
                positive.getView().setBackgroundColor(Color.GREEN);
                positive.show();
                clearFields();
            }
        });
    }

    @Override
    public void onFailure(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast negative = Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT);
                negative.getView().setBackgroundColor(Color.RED);
                negative.show();
            }
        });
    }

    private void clearFields() {
        this.nameField.setText("");
        this.ageField.setText("");
        this.addressField.setText("");
        this.positionField.setText("");
        this.licenseField.setText("");
    }
}
