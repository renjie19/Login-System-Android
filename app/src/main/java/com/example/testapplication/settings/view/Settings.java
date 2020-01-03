package com.example.testapplication.settings.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.testapplication.R;
import com.example.testapplication.utils.Preferences;

public class Settings extends AppCompatActivity {
    private EditText serverAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);
        serverAddress = findViewById(R.id.ipAddress);
        serverAddress.setText(Preferences.getAddress());
        findViewById(R.id.setting_btn).setOnClickListener(v -> finish());
    }

    @Override
    protected void onStop() {
        Preferences.setAddress(serverAddress.getText().toString());
        super.onStop();
    }
}
