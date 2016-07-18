package com.example.wilson.customchat.home.porfile;

/**
 * Created by wilson on 15/07/2016.
 */
public interface ProfileView {

    void showProgressDialog();
    void hideProgressDialog();
    String getActualState();
    void updateStateInView(String state);
    void changeState();
    void onStateDialogFinished(String newState);
    void signOff();
}
