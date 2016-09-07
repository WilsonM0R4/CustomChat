package com.example.wilson.customchat.home.porfile;

/**
 * Created by wilson on 15/07/2016.
 */
public interface ProfileInteractor {
    String getUsername();
    String getUserEmail();
    void signOff();
    void changeState(String state);
    void changeUsername(String newUsername);
    void changeAvailability(String availability);
    String getActualStatus();

}
