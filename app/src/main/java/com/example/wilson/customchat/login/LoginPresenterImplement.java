package com.example.wilson.customchat.login;

import android.util.Log;

import com.example.wilson.customchat.R;
import com.example.wilson.customchat.lib.EventBus;
import com.example.wilson.customchat.lib.GreenRobotEventBus;
import com.example.wilson.customchat.login.events.LoginEvent;

import java.util.Map;

/**
 * Created by gparrrado on 7/13/16.
 */
public class LoginPresenterImplement implements LoginPresenter {

    LoginInteractor loginInteractor;
    LoginActivity loginView;
    EventBus eventBus ;

    public LoginPresenterImplement(LoginActivity loginView){
        this.loginView = loginView;
        loginInteractor = new LoginInteractorImplement();
        this.eventBus = GreenRobotEventBus.getInstance();

    }


    @Override
    public void onCreate() {
        eventBus.register(this);
        instantiateAuthStateListener();
        loginInteractor.startAuthStateListener();
        checkForActualSessionState();
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        loginInteractor.stopAuthStateListener();
    }

    @Override
    public void checkForActualSessionState() {
        boolean session = loginInteractor.checkForActualSessionStatus();
        if(session){
            loginView.navigateToHomeScreen();
        }

    }

    @Override
    public void instantiateAuthStateListener() {
        loginInteractor.instantiateAuthStateListener();
    }

    @Override
    public void signIn(String email, String password) {
        if((email==null || email.isEmpty())||(password==null || password.isEmpty())){
            if(email==null || email.isEmpty())
                loginView.etEmail.setError(loginView.getString(R.string.empty_field_message));
            if(password==null || password.isEmpty())
                loginView.etPass.setError(loginView.getString(R.string.empty_field_message));
        }else{
            if(loginView!=null) {
                loginView.disableInputs();
                loginView.showProgresDialog();
            }
            loginInteractor.signIn(email,password);
            Log.e("presenter login","sign in requested");
        }

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
            loginView.etPass.setError(loginView.getString(R.string.check_credentials_message));
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
