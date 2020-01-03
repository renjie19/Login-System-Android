package com.example.testapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.testapplication.LoginSystem;

public class Preferences {

    private static Preferences preferences;
    private static SharedPreferences sharedPreferences = LoginSystem.context.getSharedPreferences("setting", Context.MODE_PRIVATE);

    public static void initialize() {
        if(preferences == null) {
            preferences = new Preferences();
        }
    }

    public static void setAddress(String addess) {
        sharedPreferences.edit().putString("address",addess).apply();
    }

    public static String getAddress() {
        return sharedPreferences.getString("address", "0.0.0.0");
    }
}
