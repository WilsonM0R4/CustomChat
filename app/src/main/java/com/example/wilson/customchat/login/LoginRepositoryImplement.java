package com.example.wilson.customchat.login;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.wilson.customchat.domain.FirebaseHelper;
import com.example.wilson.customchat.lib.EventBus;
import com.example.wilson.customchat.lib.GreenRobotEventBus;
import com.example.wilson.customchat.login.events.LoginEvent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by gparrrado on 7/13/16.
 */
public class LoginRepositoryImplement implements LoginRepository{

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseHelper firebaseHelper;
    boolean signInResult;

    public LoginRepositoryImplement(){
        firebaseHelper = FirebaseHelper.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void startAuthStateListener() {
        if(firebaseAuth!=null){
            firebaseAuth.addAuthStateListener(authStateListener);
        }

    }

    @Override
    public void stopAuthStateListener() {
        if(authStateListener!=null){
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }


    @Override
    public void signIn(final String email, final String password) {

        if(authStateListener==null) {
            authStateListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            signInResult = task.isSuccessful();
                            Log.e("sign in result","sign in is "+signInResult);
                            postEvent(signInResult);
                        }
                    });
                }
            };
        }

    }

    @Override
    public boolean getSignInResult() {
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

    }

}
