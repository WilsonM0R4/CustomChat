package com.example.wilson.customchat.home.porfile;

import com.example.wilson.customchat.domain.FirebaseHelper;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by wilson on 15/07/2016.
 */
public class ProfileRepositoryImplement implements ProfileRepository {

    private FirebaseHelper helper;
    private String userEmail;

    public ProfileRepositoryImplement(){
        helper = FirebaseHelper.getInstance();
    }

    @Override
    public void getUserData() {
        FirebaseUser currentUser = helper.getCurrentUserReference();
        if(currentUser!=null){

        }
    }

    @Override
    public void editProfile() {

    }

    @Override
    public void signOff() {
        helper.signOff();
    }
}
