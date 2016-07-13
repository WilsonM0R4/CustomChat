package com.example.wilson.customchat;

import android.app.Application;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseApp;

/**
 * Created by gparrrado on 7/13/16.
 */
public class CustomChatApp extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        setupFirebase();
    }

    private void setupFirebase() {
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }

}
