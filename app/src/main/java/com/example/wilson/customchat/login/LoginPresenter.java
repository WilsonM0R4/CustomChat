package com.example.wilson.customchat.login;

import com.example.wilson.customchat.login.events.LoginEvent;

/**
 * Created by gparrrado on 7/13/16.
 */
public interface LoginPresenter {

    void getLoginActivityReference(LoginActivity loginActivity);
    void startAuthStateListener();
    void stopAuthStateListener();
    void signIn(String email, String password);
    void onSignInSuccess();
    void onSignInError();
    void onEventMainThread(LoginEvent event);

}
