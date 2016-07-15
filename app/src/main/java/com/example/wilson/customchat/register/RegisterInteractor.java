package com.example.wilson.customchat.register;

/**
 * Created by wmora on 7/14/16.
 */
public interface RegisterInteractor {

    void signIn(String email, String password);
    void signUp(String email, String password);
    boolean getSignInResult();

}
