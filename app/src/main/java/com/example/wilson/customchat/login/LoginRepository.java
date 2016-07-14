package com.example.wilson.customchat.login;

/**
 * Created by gparrrado on 7/13/16.
 */
public interface LoginRepository {

    void startAuthStateListener();
    void stopAuthStateListener();
    void instantiateAuthStateListener();
    void signIn(String email, String password);
    boolean getSignInResult();

}
