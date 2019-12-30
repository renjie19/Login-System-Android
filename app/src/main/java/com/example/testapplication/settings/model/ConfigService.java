package com.example.testapplication.settings.model;

public interface ConfigService {
    void saveConfig(Config config);
    Config getConfig();
}
