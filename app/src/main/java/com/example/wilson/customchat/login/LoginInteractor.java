package com.example.wilson.customchat.login;

import java.util.Map;

/**
 * Created by gparrrado on 7/13/16.
 */
public interface LoginInteractor {
    void instantiateAuthStateListener();
    void startAuthStateListener();
    void stopAuthStateListener();
    boolean checkForActualSessionStatus();
    void signIn(String email, String password);
    boolean getSignInResult();
}
