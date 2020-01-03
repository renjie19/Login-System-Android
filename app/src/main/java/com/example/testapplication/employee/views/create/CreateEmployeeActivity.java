package com.example.testapplication.employee.views.create;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testapplication.employee.presenter.create.CreatePresenter;
import com.example.testapplication.employee.presenter.create.CreatePresenterImpl;
import com.example.testapplication.license.License;
import com.example.testapplication.R;
import com.example.testapplication.employee.model.Employee;


public class CreateEmployeeActivity extends AppCompatActivity implements CreateEmployeeView{

    private CreatePresenter presenter;
    private EditText nameField;
    private EditText ageField;
    private EditText addressField;
    private EditText positionField;
    private EditText licenseField;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_create);
        this.presenter = new CreatePresenterImpl(this);

        this.nameField = findViewById(R.id.nameField);
        this.ageField = findViewById(R.id.ageField);
        this.addressField = findViewById(R.id.addressField);
        this.positionField = findViewById(R.id.positionField);
        this.licenseField = findViewById(R.id.licenseField);

        findViewById(R.id.submitButton).setOnClickListener(v -> {
            if(!isEmpty()) {
                License license = new License();
                license.setLicenseNumber(Integer.parseInt(licenseField.getText().toString()));
                final Employee employee = new Employee();
                employee.setName(nameField.getText().toString());
                employee.setAge(Integer.parseInt(ageField.getText().toString()));
                employee.setAddress(addressField.getText().toString());
                employee.setPosition(positionField.getText().toString());
                employee.setLicense(license);
                presenter.save(employee);
            } else {
                Toast.makeText(getApplicationContext(), "Fill in all fields", Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean isEmpty() {
        return nameField.getText().toString().equals("") &&
                ageField.getText().toString().equals("") &&
                addressField.getText().toString().equals("") &&
                positionField.getText().toString().equals("") &&
                licenseField.getText().toString().equals("");
    }

    @Override
    public void clearFields() {
        this.nameField.setText("");
        this.ageField.setText("");
        this.addressField.setText("");
        this.positionField.setText("");
        this.licenseField.setText("");
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
    }
}
