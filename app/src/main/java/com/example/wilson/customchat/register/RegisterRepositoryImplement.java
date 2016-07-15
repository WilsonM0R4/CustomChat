package com.example.wilson.customchat.register;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.wilson.customchat.lib.EventBus;
import com.example.wilson.customchat.lib.GreenRobotEventBus;
import com.example.wilson.customchat.register.events.RegisterEvents;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by wmora on 7/14/16.
 */
public class RegisterRepositoryImplement implements RegisterRepository{

    FirebaseAuth firebaseAuth;
    //RegisterInteractor registerInteractor;
    boolean signUpResult;
    final int[] regResult = new int[]{RegisterEvents.onWaitingForResult};

    public RegisterRepositoryImplement(){
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void signUp(String email, String password) {

        if(firebaseAuth==null)
            firebaseAuth = FirebaseAuth.getInstance();

        Log.e("repository register","register requested");
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.e("Register repo","task successful");
                    regResult[0] = RegisterEvents.onRegisterSuccess;
                    signUpResult = true;
                }else{
                    Log.e("Register repo","task failure"+task.getResult());
                    regResult[0] = RegisterEvents.onRegisterError;
                    signUpResult = false;
                }
                postEvent(regResult[0]);
            }
        });

    }

    @Override
    public boolean getSignUpResult() {
        return signUpResult;
    }

    private void postEvent(int type){
        postEvent(type,null);
    }

    private void postEvent(int type,String errorMessage){
        RegisterEvents regEvent = new RegisterEvents();
        regEvent.setEventType(type);
        if(errorMessage != null){
            regEvent.setErrorMessage(errorMessage);
        }
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(regEvent);
        Log.e("presenter event","event posted, type "+type);
    }

}
