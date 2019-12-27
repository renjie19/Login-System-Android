package com.example.testapplication;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
public class LoginSystem extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
    }
}
