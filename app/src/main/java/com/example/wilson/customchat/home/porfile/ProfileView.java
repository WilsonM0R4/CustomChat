package com.example.wilson.customchat.home.porfile;

/**
 * Created by wilson on 15/07/2016.
 */
public interface ProfileView {

    String getActualState();
    void editProfile();
    void updateStateInView(String state);
    void changeState();
    void onStateDialogFinished(String newState);
    void changeAvailability();
    void onAvailabilityDialogFinished(String availability);
    void signOff();
}
