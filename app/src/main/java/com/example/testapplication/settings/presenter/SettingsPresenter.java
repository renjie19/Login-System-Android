package com.example.testapplication.settings.presenter;

import com.example.testapplication.settings.model.Config;
import com.example.testapplication.settings.model.ConfigService;
import com.example.testapplication.settings.model.ConfigServiceImpl;

public class SettingsPresenter {
    private ConfigService service;

    public SettingsPresenter() {
        this.service = new ConfigServiceImpl();
    }

    public void saveConfig(Config config) {
        service.saveConfig(config);
    }

    public Config getConfig() {
        return service.getConfig();
    }
}
