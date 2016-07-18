package com.example.wilson.customchat.home.porfile;

import android.util.Log;

import com.example.wilson.customchat.domain.FirebaseHelper;
import com.example.wilson.customchat.home.porfile.events.ProfileEvents;
import com.example.wilson.customchat.lib.EventBus;
import com.example.wilson.customchat.lib.GreenRobotEventBus;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by wilson on 15/07/2016.
 */
public class ProfileRepositoryImplement implements ProfileRepository {

    private FirebaseHelper helper;
    private String userEmail;
    private String actualStatus;
    private FirebaseUser currentUser;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private Map<String,String> userData;

    public ProfileRepositoryImplement(){
        helper = FirebaseHelper.getInstance();
        auth = helper.getFirebaseAuth();
        userData = new HashMap<>();
        databaseReference = helper.getDatabaseReference();
        databaseReference.child("user_extra_data").child("status").addValueEventListener(valueEventListener());
    }

    @Override
    public void startDatabaseListener() {

    }

    @Override
    public String getUserEmail() {
        getUserData();
        return currentUser.getEmail();
    }

    @Override
    public void getUserData() {
        currentUser = helper.getCurrentUserReference();
        if(currentUser!=null){

        }
    }

    @Override
    public void editProfile() {

    }

    @Override
    public void changeState(String state) {
        final String statusString = "status";
        Log.e("change state","received state is "+state);
        String data = helper.getUserExtraData().child(statusString).toString();
        Map<String,Object> newState = new HashMap<>();
        newState.put(statusString,state);
        databaseReference.child("user_extra_data").child("status").setValue(state);
        Log.e("user node",data);

    }

    @Override
    public String getActualStatus() {
        //actualStatus = helper.getUserExtraData().child("status").child("state").toString();
        return actualStatus;
    }


    @Override
    public void signOff() {
        Log.e("Profile repo","Sign off requested");
        auth.signOut();
    }

    private ValueEventListener valueEventListener(){

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                actualStatus = dataSnapshot.getValue(String.class);
                if(actualStatus!=null){
                    postEvent(true);
                }

                Log.e("dataSnapshot","status is "+actualStatus);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                postEvent(false,databaseError.getMessage());
                Log.e("event error","an error has occurred: "+databaseError.getMessage());
            }
        };

        return listener;
    }

    private void postEvent(boolean type){
        postEvent(type,null);
    }
    private void postEvent(boolean type,String errorMessage){
        EventBus eventBus = GreenRobotEventBus.getInstance();
        ProfileEvents event = new ProfileEvents();
        if (errorMessage!=null){
            event.setErrorMessage(errorMessage);
        }
        event.setEventType(type);
        eventBus.post(event);
    }
}
