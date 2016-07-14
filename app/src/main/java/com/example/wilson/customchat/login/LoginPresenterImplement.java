package com.example.wilson.customchat.login;

import android.util.Log;

import com.example.wilson.customchat.lib.EventBus;
import com.example.wilson.customchat.lib.GreenRobotEventBus;
import com.example.wilson.customchat.login.events.LoginEvent;

/**
 * Created by gparrrado on 7/13/16.
 */
public class LoginPresenterImplement implements LoginPresenter {

    LoginInteractor loginInteractor;
    LoginActivity loginView;
    EventBus eventBus ;

    public LoginPresenterImplement(){
        loginInteractor = new LoginInteractorImplement();
        this.eventBus = GreenRobotEventBus.getInstance();

    }

    @Override
    public void getLoginActivityReference(LoginActivity loginActivity) {
        loginView = loginActivity;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
        instantiateAuthStateListener();
        loginInteractor.startAuthStateListener();
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        loginInteractor.stopAuthStateListener();
    }

    @Override
    public void instantiateAuthStateListener() {
        loginInteractor.instantiateAuthStateListener();
    }

    @Override
    public void signIn(String email, String password) {
        if(loginView!=null) {
            loginView.disableInputs();
            loginView.showProgresDialog();
        }
        loginInteractor.signIn(email,password);
        Log.e("presenter login","sign in requested");
    }

    @Override
    public void onSignInSuccess() {
        if(loginView!=null){
            loginView.enableInputs();
            loginView.hideProgresDialog();
            loginView.navigateToHomeScreen();
        }
    }

    @Override
    public void onSignInError() {
        if(loginView!=null){
            loginView.enableInputs();
            loginView.hideProgresDialog();
        }
    }

    @Override
    public void onEventMainThread(LoginEvent event) {

        boolean loginEvent = loginInteractor.getSignInResult();

        if (loginEvent){
            onSignInSuccess();
            Log.e("login event","session event is "+loginEvent);
        }else{
            onSignInError();
            Log.e("login event","session event is "+loginEvent);
        }
    }

}
