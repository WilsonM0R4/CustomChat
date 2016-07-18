package com.example.wilson.customchat.domain;

import android.util.Log;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    private static final String USERS_PATH = "users";
    private static final String CHATS_PATH = "chats";
    private static final String CONTACTS_PATH = "contacts";
    private static final String SEPARATOR = "__";

    private static class SingletonHolder{
        private static final FirebaseHelper INSTANCE = new FirebaseHelper();
    }

    public static FirebaseHelper getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public FirebaseHelper (){
        this.dataReference = new Firebase(FIREBASE_URL);
        firebaseAuth = FirebaseAuth.getInstance();

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
        return FirebaseDatabase.getInstance().getReference();
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

    public void signOff(){
        if(firebaseUser!=null){
            firebaseAuth.signOut();
        }else{
            Log.e("Helper message","user is signed out yet");
        }
    }

}
