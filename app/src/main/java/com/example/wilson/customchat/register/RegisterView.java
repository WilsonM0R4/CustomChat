package com.example.wilson.customchat.register;

/**
 * Created by wmora on 7/13/16.
 */
public interface RegisterView {

    void enableInputs();
    void disableInputs();
    void showProgresDialog();
    void hideProgressDialog();
    void handleSignUp();
    void onCancelPressed();
    void showSnackBar();

}
