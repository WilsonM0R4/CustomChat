package com.example.wilson.customchat.domain;

import android.util.Log;

import com.example.wilson.customchat.User;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gparrrado on 7/13/16.
 */
public class FirebaseHelper {

    private Firebase dataReference;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private static final String FIREBASE_URL = "https://customchat-a7c4f.firebaseio.com/";
    private static final String USER_EXTRA_INFO = "user_extra_data";
    public static final String USERS_PATH = "registered_users";
    private static final String CHATS_PATH = "chats";
    private static final String CONTACTS_PATH = "contacts";
    private static final String SEPARATOR = "__";
    public static final String REGISTERED_USER_KEY = "user_key_";


    private static class SingletonHolder{
        private static final FirebaseHelper INSTANCE = new FirebaseHelper();
    }

    public static FirebaseHelper getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public FirebaseHelper (){
        this.dataReference = new Firebase(FIREBASE_URL);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public String getBaseUrl(){
        return FIREBASE_URL;
    }

    public Firebase getDataReference() {
        return dataReference;
    }

    public void setDataReference(Firebase dataReference) {
        this.dataReference = dataReference;
    }

    public DatabaseReference getDatabaseReference(){
        return databaseReference;
    }

    public Firebase getUserExtraData(){
        return dataReference.child(USER_EXTRA_INFO);
    }

    public FirebaseAuth getFirebaseAuth(){
        return firebaseAuth;
    }

    public FirebaseUser getCurrentUserReference(){
        firebaseUser = firebaseAuth.getCurrentUser();
        return firebaseUser;
    }

    public void changeUserAvailability(String availability){
        if(databaseReference!=null){
            databaseReference.child(User.EXTRA_DATA_KEY).child(User.formatEmail(firebaseUser.getEmail())).child(User.USER_AVALIABILITY).setValue(availability);
        }
    }

    public void getRegisteredUsers(){

    }


    public void signOff(){
        if(firebaseUser!=null){
            firebaseAuth.signOut();
        }else{
            Log.e("Helper message","user is signed out yet");
        }
    }

}
