package com.example.wilson.customchat.login;

/**
 * Created by gparrrado on 7/13/16.
 */
public interface LoginInteractor {
    void instantiateAuthStateListener();
    void startAuthStateListener();
    void stopAuthStateListener();
    void signIn(String email, String password);
    boolean getSignInResult();
}
