package com.example.wilson.customchat.register;

/**
 * Created by wmora on 7/14/16.
 */
public interface RegisterInteractor {

    void signUp(String username, String email, String password);
    boolean getSignUpResult();

}
