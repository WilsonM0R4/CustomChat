package com.example.wilson.customchat;

import java.util.Map;

/**
 * Created by wmora on 7/13/16.
 */
public class User {

    public static final String DEFAULT_STATE ="usando CustomChat!";
    public static final String NONE_IMAGE = "none";
    public static final String USER_ONLINE = "online";
    public static final String USER_OFFLINE = "offline";
    public static final boolean USER_LOGED_IN = true;
    public static final boolean USER_LOGED_OUT = false;
    public static final String EMAIL_KEY = "email";
    public static final String EXTRA_DATA_KEY = "user_extra_data";
    public static final String USER_AVALIABILITY = "availavility";
    public static final String USER_PROFILE_IMAGE = "profile_image";
    public static final String USER_STATE = "status";
    public static final String USERNAME = "username";
    public static final String USER_CONTACTS = "contacts";
    public static final String USER_NO_CONTACTS = "no contacts";
    Map<String,String> userData;

    public static class UserSingleton{
        public static final User INSTANCE = new User();
    }

    public static User getInstance(){
        return UserSingleton.INSTANCE;
    }

    public void allocUserData(Map<String,String> userData){
        this.userData = userData;
    }

    public Map getUserData(){
        return userData;
    }

    public static String formatEmail(String email){

        if(email.contains("."))
            email = email.replace(".","_");

        if(email.contains("#"))
            email = email.replace("#","_");

        if(email.contains("$"))
            email = email.replace("$","_");

        if(email.contains("["))
            email = email.replace("[","_");

        if(email.contains("]"))
            email = email.replace("]","_");

        return email;

    }

    public static String registeredUserKey(String mainKey, String userEmail){
        userEmail = formatEmail(userEmail);

        return mainKey+userEmail;
    }


}
