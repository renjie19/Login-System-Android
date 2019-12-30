package com.example.testapplication.settings.model;

public interface ConfigResource {
    void saveConfig(Config config);
    Config getConfig();
}
