package com.example.wilson.customchat.register;

import com.example.wilson.customchat.register.events.RegisterEvents;

/**
 * Created by wmora on 7/14/16.
 */
public interface RegisterPresenter {

    void onCreate();
    void onDestroy();
    //void checkForActualSessionState();
    void instantiateAuthStateListener();
    void signUp(String email, String password);
    void onSignUpSuccess();
    void onSignUpError();
    void onEventMainThread(RegisterEvents event);

}
