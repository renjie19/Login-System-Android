package com.example.testapplication.utils;

import com.example.testapplication.settings.model.Config;
import com.example.testapplication.settings.presenter.SettingsPresenter;

public class Server {

    private static Config config;
    private static SettingsPresenter presenter;

    private Server(){
    }

    public static void initialize() {
        presenter = new SettingsPresenter();
        Config result = presenter.getConfig();
        if(result == null) {
            config = new Config();
        } else {
            config = result;
        }
    }

    public static Config getConfig() {
        if(config == null) {
            config = new Config();
        }
        return config;
    }
}
