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
import com.example.testapplication.timelog.TimeLogCallBack;
import com.example.testapplication.timelog.model.TimeLog;
import com.example.testapplication.timelog.model.TimelogRest;
import com.example.testapplication.timelog.presenter.TimeLogPresenter;

import java.util.Date;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements TimeLogCallBack {

    private EmployeePresenter presenter;
    private TimeLogPresenter timeLogPresenter;
    private EditText idField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.presenter = new EmployeePresenter(null);
        this.timeLogPresenter = new TimeLogPresenter(this);


        findViewById(R.id.manage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent manage = new Intent(getApplicationContext(), ManageEmployee.class);
                startActivity(manage);
            }
        });

        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idField = findViewById(R.id.idField);
                timeLogPresenter.login(Integer.parseInt(idField.getText().toString()));
//                Retrofit login = new Retrofit.Builder().baseUrl("http://172.16.0.172:8080").addConverterFactory(GsonConverterFactory.create()).build();
//                TimelogRest timelogRest = login.create(TimelogRest.class);
//                if(!idField.getText().toString().equals("")){
//                    timelogRest.login().enqueue(new Callback<TimeLog>() {
//                        @Override
//                        public void onResponse(Call<TimeLog> call, Response<TimeLog> response) {
//                            if (!response.isSuccessful()) {
//                                Toast.makeText(getApplicationContext(), "Id does not Exist", Toast.LENGTH_SHORT).show();
//                            } else {
//                                idField.setText("");
//                                Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<TimeLog> call, Throwable t) {
//                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                } else {
//                    Toast.makeText(getApplicationContext(), "Fill in required fields",Toast.LENGTH_LONG).show();
//                }
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
        Toast success = Toast.makeText(this, "Login Successful\n"+new Date(timeLog.getTime()), Toast.LENGTH_SHORT);
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
