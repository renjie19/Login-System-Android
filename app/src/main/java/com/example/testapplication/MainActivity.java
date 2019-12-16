package com.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.testapplication.employee.presenter.ManageEmployee;
import com.example.testapplication.timelog.model.TimeLog;
import com.example.testapplication.timelog.model.TimelogRest;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                final EditText idField = findViewById(R.id.idField);
                Retrofit login = new Retrofit.Builder().baseUrl("http://172.16.0.172:8080").addConverterFactory(GsonConverterFactory.create()).build();
                TimelogRest timelogRest = login.create(TimelogRest.class);
                if(!idField.getText().toString().equals("")){
                    timelogRest.login(Integer.parseInt(idField.getText().toString())).enqueue(new Callback<TimeLog>() {
                        @Override
                        public void onResponse(Call<TimeLog> call, Response<TimeLog> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                            } else {
                                idField.setText("");
                                Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<TimeLog> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Fill in required fields",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
