package com.example.wilson.customchat.login;

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
    public void startAuthStateListener() {
        loginRepository.startAuthStateListener();
    }

    @Override
    public void stopAuthStateListener() {
        loginRepository.stopAuthStateListener();
    }

    @Override
    public void signIn(String email, String password) {
        loginRepository.signIn(email,password);
    }

}
