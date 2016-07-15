package com.example.wilson.customchat.login;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.wilson.customchat.User;
import com.example.wilson.customchat.domain.FirebaseHelper;
import com.example.wilson.customchat.lib.EventBus;
import com.example.wilson.customchat.lib.GreenRobotEventBus;
import com.example.wilson.customchat.login.events.LoginEvent;
import com.example.wilson.customchat.register.events.RegisterEvents;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by gparrrado on 7/13/16.
 */
public class LoginRepositoryImplement implements LoginRepository{

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseHelper firebaseHelper;
    FirebaseUser user;
    boolean signInResult;
    boolean signUpResult;
    final int[] loginResult = new int[]{LoginEvent.onWaitingForResult};
    final int[] regResult = new int[]{RegisterEvents.onWaitingForResult};

    public LoginRepositoryImplement(){
        firebaseHelper = FirebaseHelper.getInstance();
    }

    @Override
    public void startAuthStateListener() {
        if(firebaseAuth==null){
            firebaseAuth = FirebaseAuth.getInstance();
            Log.e("FirebaseAuth","auth is not null ");
        }
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    public void stopAuthStateListener() {
        if(authStateListener!=null){
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

    @Override
    public void instantiateAuthStateListener() {
        if(firebaseAuth==null) {
            firebaseAuth = FirebaseAuth.getInstance();
        }
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();

                if(user!=null){
                    signInResult = User.USER_LOGED_IN;
                    Log.e("User signed in","user is "+user.getEmail());
                }else{
                    signInResult = User.USER_LOGED_OUT;
                }
            }
        };
    }

    @Override
    public boolean checkForActualSessionStatus() {
        user = firebaseAuth.getCurrentUser();

        if (user!=null){
            return User.USER_LOGED_IN;
        }else{
            return User.USER_LOGED_OUT;
        }

    }


    @Override
    public void signIn(final String email, final String password) {

        Log.e("repository login","sign in requested");

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    loginResult[0] = LoginEvent.onSignInSuccess;
                }else{
                    loginResult[0] = LoginEvent.onSignInError;
                }
                //signInResult = task.isSuccessful();
                Log.e("sign in result","sign in is "+signInResult);
                postEvent(signInResult);
            }
        });

    }

    @Override
    public void signUp(String email, String password) {

        Log.e("repository register","register requested");
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    regResult[0] = RegisterEvents.onRegisterSuccess;
                    signInResult = true;
                }else{
                    regResult[0] = RegisterEvents.onRegisterError;
                    signInResult = false;
                }
            }
        });

    }

    @Override
    public boolean getSignUpResult() {
        return signUpResult;
    }

    @Override
    public boolean getSignInResult() {
        Log.e("repo sign in result","sign in is "+signInResult);
        return signInResult;
    }

    private void postEvent(boolean type){
        postEvent(type,null);
    }

    private void postEvent(boolean type,String errorMessage){
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        if(errorMessage != null){
            loginEvent.setErrorMessage(errorMessage);
        }
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);
        Log.e("presenter event","event posted, type "+type);
    }

}
