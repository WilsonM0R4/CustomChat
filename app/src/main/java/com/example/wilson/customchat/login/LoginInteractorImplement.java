package com.example.wilson.customchat.login;

import android.util.Log;

/**
 * Created by gparrrado on 7/13/16.
 */
public class LoginInteractorImplement implements LoginInteractor{

    public static final boolean SIGN_IN_SUCCESS = true;
    public static final boolean SIGN_IN_FAILURE = false;
    private LoginRepository loginRepository;

    public LoginInteractorImplement(){
        loginRepository = new LoginRepositoryImplement();
    }

    @Override
    public boolean getSignInResult() {
        return loginRepository.getSignInResult();
    }

    @Override
    public void instantiateAuthStateListener() {
        loginRepository.instantiateAuthStateListener();
    }

    @Override
    public void startAuthStateListener() {
        loginRepository.startAuthStateListener();
    }

    @Override
    public void stopAuthStateListener() {
        loginRepository.stopAuthStateListener();
    }

    @Override
    public boolean checkForActualSessionStatus() {
        return loginRepository.checkForActualSessionStatus();
    }

    @Override
    public void signIn(String email, String password) {

        if((email !=null && !email.isEmpty()) && (password !=null && !password.isEmpty()) ){
            loginRepository.signIn(email,password);
        }else{
            Log.e("credentials","user or password null");
        }


        Log.e("interactor login","sign in requested");
    }

    @Override
    public void signUp(String email, String password) {
        if((email !=null && !email.isEmpty()) && (password !=null && !password.isEmpty()) )
            loginRepository.signUp(email,password);
        else
            Log.e("interactor sign up","user or password null");
    }

    @Override
    public LoginRepository getRepositoryInstance() {
        return loginRepository;
    }

}
