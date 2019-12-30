package com.example.testapplication.settings.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.testapplication.R;
import com.example.testapplication.settings.model.Config;
import com.example.testapplication.settings.presenter.SettingsPresenter;
import com.example.testapplication.utils.Server;

public class Settings extends AppCompatActivity {
    private SettingsPresenter presenter;
    private EditText serverAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);

        presenter = new SettingsPresenter();
        serverAddress = findViewById(R.id.ipAddress);
        serverAddress.setText(Server.getConfig().getServerAddress());
        findViewById(R.id.setting_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void saveConfig() {
        Config config = Server.getConfig();
        config.setServerAddress(serverAddress.getText().toString());
        presenter.saveConfig(config);
    }

    @Override
    protected void onStop() {
        saveConfig();
        super.onStop();
    }
}
