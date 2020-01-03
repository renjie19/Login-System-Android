package com.example.testapplication.employee.views.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testapplication.R;
import com.example.testapplication.employee.presenter.main.MainPresenter;
import com.example.testapplication.employee.presenter.main.MainPresenterImpl;
import com.example.testapplication.employee.views.manage.ManageEmployeeActivity;
import com.example.testapplication.settings.view.Settings;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter presenter;
    private EditText idField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.presenter = new MainPresenterImpl(this);

        findViewById(R.id.manage).setOnClickListener(v -> {
            Intent manage = new Intent(getApplicationContext(), ManageEmployeeActivity.class);
            startActivity(manage);
        });

        findViewById(R.id.settings_btn).setOnClickListener(v -> {
            Intent settings = new Intent(getApplicationContext(), Settings.class);
            startActivity(settings);
        });

        findViewById(R.id.loginButton).setOnClickListener(v -> {
            idField = findViewById(R.id.idField);
            if(!idField.getText().toString().equals("")) {
                presenter.login(Integer.parseInt(idField.getText().toString()));
            } else {
                Toast.makeText(getApplicationContext(), "Fill in all Fields", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.sync();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Realm.getDefaultInstance().close();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearFields() {
        idField.setText("");
    }
}
