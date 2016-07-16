package com.example.wilson.customchat.home.porfile;

import com.example.wilson.customchat.domain.FirebaseHelper;

/**
 * Created by wilson on 15/07/2016.
 */
public interface ProfileRepository {

    void getUserData();
    void editProfile();
    void signOff();

}
