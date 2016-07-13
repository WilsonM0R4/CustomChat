package com.example.wilson.customchat.domain;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by gparrrado on 7/13/16.
 */
public class FirebaseHelper {

    private Firebase dataReference;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private static final String FIREBASE_URL = "https://customchat-a7c4f.firebaseio.com/";
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

    public Firebase getDataReference() {
        return dataReference;
    }

    public void setDataReference(Firebase dataReference) {
        this.dataReference = dataReference;
    }

    public void getUserReference(String email){

    }

    public FirebaseUser getCurrentUserReference(){
        firebaseUser = firebaseAuth.getCurrentUser();
        return firebaseUser;
    }

}
