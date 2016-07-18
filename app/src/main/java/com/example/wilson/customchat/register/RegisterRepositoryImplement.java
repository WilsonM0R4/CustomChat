package com.example.wilson.customchat.register;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.wilson.customchat.User;
import com.example.wilson.customchat.domain.FirebaseHelper;
import com.example.wilson.customchat.lib.EventBus;
import com.example.wilson.customchat.lib.GreenRobotEventBus;
import com.example.wilson.customchat.register.events.RegisterEvents;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by wmora on 7/14/16.
 */
public class RegisterRepositoryImplement implements RegisterRepository{

    FirebaseAuth firebaseAuth;
    FirebaseHelper helper;
    //RegisterInteractor registerInteractor;
    boolean signUpResult;
    final int[] regResult = new int[]{RegisterEvents.onWaitingForResult};

    public RegisterRepositoryImplement(){
        helper = FirebaseHelper.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void signUp(final String username, final String email, String password) {

        if(firebaseAuth==null)
            firebaseAuth = FirebaseAuth.getInstance();

        Log.e("repository register","register requested");
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.e("Register repo","task successful");
                    regResult[0] = RegisterEvents.onRegisterSuccess;

                    FirebaseUser user = firebaseAuth.getCurrentUser();

                    if(user!=null)
                        createUserExtraDataPath(email,username);

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

    @Override
    public void createUserExtraDataPath(String userEmail,String username) {
        DatabaseReference database = helper.getDatabaseReference().child(User.EXTRA_DATA_KEY);
        String userExtraDataPathId = User.formatEmail(userEmail);
        Map<String,Object> userData = new HashMap<>();
        userData.put(User.USERNAME,username);
        userData.put(User.USER_STATE,User.DEFAULT_STATE);
        userData.put(User.USER_AVALIABILITY, String.valueOf(User.USER_ONLINE));
        userData.put(User.USER_PROFILE_IMAGE, User.NONE_IMAGE);

        database.child(userExtraDataPathId).updateChildren(userData);
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
