package com.example.testapplication.settings.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Config extends RealmObject {
    @PrimaryKey
    private int id;
    private String serverAddress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }
}
