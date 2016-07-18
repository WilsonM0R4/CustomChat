package com.example.wilson.customchat.login;

import com.example.wilson.customchat.login.events.LoginEvent;

import java.util.Map;

/**
 * Created by gparrrado on 7/13/16.
 */
public interface LoginPresenter {

    void onCreate();
    void onDestroy();
    void checkForActualSessionState();
    void instantiateAuthStateListener();
    void signIn(String email, String password);
    void onSignInSuccess();
    void onSignInError();
    void onEventMainThread(LoginEvent event);

}
