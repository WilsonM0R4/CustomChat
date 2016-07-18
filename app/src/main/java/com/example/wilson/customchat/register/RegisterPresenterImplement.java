package com.example.wilson.customchat.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.example.wilson.customchat.lib.EventBus;
import com.example.wilson.customchat.lib.GreenRobotEventBus;

import com.example.wilson.customchat.login.LoginActivity;
import com.example.wilson.customchat.register.events.RegisterEvents;

/**
 * Created by wmora on 7/14/16.
 */
public class RegisterPresenterImplement implements RegisterPresenter {

    RegisterActivity registerActivity;
    RegisterInteractor interactor;
    EventBus eventBus;
    CoordinatorLayout regContainer;

    public RegisterPresenterImplement(RegisterActivity registerActivity, CoordinatorLayout regContainer){
        this.registerActivity = registerActivity;
        interactor = new RegisterInteractorImplement();
        this.regContainer = regContainer;
        eventBus = new GreenRobotEventBus();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }

    @Override
    public void instantiateAuthStateListener() {

    }

    @Override
    public void signUp(String username,String email, String password) {
        if(!username.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            if(password.length()>=6){
                registerActivity.disableInputs();
                registerActivity.showProgresDialog();
                interactor.signUp(username, email, password);
            }else
                registerActivity.etRegPasss.setError("la contraseña debe tener mas de 6 caracteres");

        }
        else {
            if (email.isEmpty())
                registerActivity.etRegEmail.setError("este campo no puede ser vacío");
            if (password.isEmpty()) {
                registerActivity.etRegPasss.setError("este campo no puede ser vacío");
                registerActivity.etRegConfirmPass.setError("este campo no puede ser vacío");
            }
        }

    }

    @Override
    public void onSignUpSuccess() {
        registerActivity.hideProgressDialog();
        registerActivity.enableInputs();

        /*Intent intent = new Intent(registerActivity, LoginActivity.class);
        intent.putExtra("signed_up_mail",registerActivity.etRegEmail.getText());
        registerActivity.startActivity(intent);*/
        registerActivity.showSnackBar();

        //registerActivity.finish();
    }

    @Override
    public void onSignUpError() {
        registerActivity.hideProgressDialog();
        registerActivity.enableInputs();
    }

    @Override
    public void onEventMainThread(RegisterEvents event) {

        if(event.getEventType()){
            onSignUpSuccess();
        }else{
            onSignUpError();
        }

    }
}
