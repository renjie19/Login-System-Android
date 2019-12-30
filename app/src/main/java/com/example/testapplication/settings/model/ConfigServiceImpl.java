package com.example.testapplication.settings.model;

public class ConfigServiceImpl implements ConfigService {

    private ConfigResource resource;

    public ConfigServiceImpl() {
        this.resource = new ConfigResourceImpl();
    }

    @Override
    public void saveConfig(Config config) {
         resource.saveConfig(config);
    }

    @Override
    public Config getConfig() {
        return resource.getConfig();
    }
}
