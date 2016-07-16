package com.example.wilson.customchat.home.porfile;

import com.example.wilson.customchat.domain.FirebaseHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by wilson on 15/07/2016.
 */
public class ProfileRepositoryImplement implements ProfileRepository {

    private FirebaseHelper helper;
    private String userEmail;
    FirebaseUser currentUser;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;

    public ProfileRepositoryImplement(){
        helper = FirebaseHelper.getInstance();
        auth = helper.getFirebaseAuth();
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
    public void signOff() {
        auth.signOut();
    }
}
