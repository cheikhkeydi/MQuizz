package com.example.mquizz;

import android.app.Activity;
import android.app.Application;

import com.backendless.Backendless;

public class Defaults extends Application {

    public static final String APPLICATION_ID = "B117AFF5-0DC2-9B3E-FF92-AC2671D15700";
    public static final String API_KEY = "C741A5C6-8BED-44B2-AC93-DF7CBA4FC254";
    public static final String SERVER_URL = "https://api.backendless.com";


    public void onCreate() {
        super.onCreate();
        Backendless.setUrl(SERVER_URL);
        Backendless.initApp(getApplicationContext(), APPLICATION_ID, API_KEY);
    }



}
