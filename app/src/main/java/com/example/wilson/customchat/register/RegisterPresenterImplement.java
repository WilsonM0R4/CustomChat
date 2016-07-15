package com.example.wilson.customchat.register;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.example.wilson.customchat.login.LoginInteractor;
import com.example.wilson.customchat.login.LoginInteractorImplement;
import com.example.wilson.customchat.register.events.RegisterEvents;

/**
 * Created by wmora on 7/14/16.
 */
public class RegisterPresenterImplement implements RegisterPresenter {

    RegisterActivity registerActivity;
    LoginInteractor loginInteractor;

    public RegisterPresenterImplement(RegisterActivity registerActivity){
        this.registerActivity = registerActivity;

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void instantiateAuthStateListener() {

    }

    @Override
    public void signUp(String email, String password) {
        onSignUpSuccess();
    }

    @Override
    public void onSignUpSuccess() {

        registerActivity.showSnackBar();
    }

    @Override
    public void onSignUpError() {

    }

    @Override
    public void onEventMainThread(RegisterEvents event) {

    }
}
