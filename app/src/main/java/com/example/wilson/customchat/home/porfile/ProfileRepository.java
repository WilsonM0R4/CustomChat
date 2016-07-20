package com.example.wilson.customchat.home.porfile;

/**
 * Created by wilson on 15/07/2016.
 */
public interface ProfileRepository {

    void startDatabaseListener();
    String getUsername();
    String getUserEmail();
    void getUserData();
    void editProfile();
    void changeState(String state);
    String getActualStatus();
    void signOff();

}
