package com.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testapplication.employee.presenter.EmployeePresenter;
import com.example.testapplication.employee.views.ManageEmployee;
import com.example.testapplication.settings.presenter.SettingsPresenter;
import com.example.testapplication.settings.view.Settings;
import com.example.testapplication.timelog.TimeLogCallBack;
import com.example.testapplication.timelog.model.TimeLog;
import com.example.testapplication.timelog.presenter.TimeLogPresenter;
import com.example.testapplication.utils.Server;

import java.util.Date;
import java.util.Set;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements TimeLogCallBack {

    private EmployeePresenter presenter;
    private TimeLogPresenter timeLogPresenter;
    private EditText idField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Server.initialize();
        this.presenter = new EmployeePresenter(null);
        this.timeLogPresenter = new TimeLogPresenter(this);

        findViewById(R.id.manage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent manage = new Intent(getApplicationContext(), ManageEmployee.class);
                startActivity(manage);
            }
        });

        findViewById(R.id.settings_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settings = new Intent(getApplicationContext(), Settings.class);
                startActivity(settings);
            }
        });

        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idField = findViewById(R.id.idField);
                if(!idField.getText().toString().equals("")) {
                    timeLogPresenter.login(Integer.parseInt(idField.getText().toString()));
                } else {
                    Toast.makeText(getApplicationContext(), "Fill in all Fields", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.sync();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Realm.getDefaultInstance().close();
    }

    @Override
    public void onSuccess(TimeLog timeLog) {
        idField.setText("");
        Toast success = Toast.makeText(this, "Login Successful\n"+new Date(timeLog.getTime()).toLocaleString(), Toast.LENGTH_SHORT);
        success.getView().setBackgroundColor(Color.GREEN);
        success.show();
    }

    @Override
    public void onFailure(String message) {
        Toast failed = Toast.makeText(this, message, Toast.LENGTH_LONG);
        failed.getView().setBackgroundColor(Color.RED);
        failed.show();
    }
}
