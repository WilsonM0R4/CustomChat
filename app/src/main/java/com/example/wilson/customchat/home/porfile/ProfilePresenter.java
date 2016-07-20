package com.example.wilson.customchat.home.porfile;

import com.example.wilson.customchat.home.porfile.events.ProfileEvents;

/**
 * Created by wilson on 15/07/2016.
 */
public interface ProfilePresenter {

    void onCreate();
    void onDestroy();
    void setUserDataToView();
    String getUsername();
    String getUserEmail();
    void changeState(String state);
    String getActualState();
    void setUserStatusToView();
    void onEventMainThread(ProfileEvents event);
    void signOff();
}
