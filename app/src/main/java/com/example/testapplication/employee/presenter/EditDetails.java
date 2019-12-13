package com.example.testapplication.employee.presenter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.testapplication.R;
import com.example.testapplication.employee.model.Employee;

public class EditDetails extends AppCompatActivity {

    private EditText edit_nameField;
    private EditText edit_ageField;
    private EditText edit_addressField;
    private EditText edit_licenseField;
    private EditText edit_positionField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);
        initializeFields();
        Employee employee = (Employee)getIntent().getSerializableExtra("data");


        edit_nameField.setText(employee.getName());
        edit_ageField.setText(employee.getAge()+"");
        edit_addressField.setText(employee.getAddress());
        edit_positionField.setText(employee.getPosition());
        edit_licenseField.setText(employee.getLicense().getLicenseNumber()+"");
    }
    
    private void initializeFields(){
         edit_nameField = findViewById(R.id.edit_nameField);
         edit_ageField = findViewById(R.id.edit_ageField);
         edit_addressField = findViewById(R.id.edit_addressField);
         edit_positionField = findViewById(R.id.edit_positionField);
         edit_licenseField = findViewById(R.id.edit_licenseField);
    }
}
