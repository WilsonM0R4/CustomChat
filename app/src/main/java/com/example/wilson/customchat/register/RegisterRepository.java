package com.example.wilson.customchat.register;

/**
 * Created by wmora on 7/14/16.
 */
public interface RegisterRepository {
    void signUp(String email, String password);
    boolean getSignUpResult();
}
