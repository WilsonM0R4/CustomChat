package com.example.wilson.customchat.register;

import android.support.annotation.NonNull;
import android.util.Log;

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
    boolean signUpResult;
    final int[] regResult = new int[]{RegisterEvents.onWaitingForResult};

    public RegisterRepositoryImplement(){

    }

    @Override
    public void signUp(String email, String password) {

        Log.e("repository register","register requested");
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    regResult[0] = RegisterEvents.onRegisterSuccess;
                    signUpResult = true;
                }else{
                    regResult[0] = RegisterEvents.onRegisterError;
                    signUpResult = false;
                }
            }
        });

    }

    @Override
    public boolean getSignUpResult() {
        return signUpResult;
    }

}
