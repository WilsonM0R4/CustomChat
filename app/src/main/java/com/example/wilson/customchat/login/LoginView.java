package com.example.wilson.customchat.login;

/**
 * Created by gparrrado on 7/13/16.
 */
public interface LoginView {

    void showProgresDialog();
    void hideProgresDialog();
    void enableInputs();
    void disableInputs();
    void handleSignIn();
    void onSignUpPressed();
    void navigateToHomeScreen();

}
