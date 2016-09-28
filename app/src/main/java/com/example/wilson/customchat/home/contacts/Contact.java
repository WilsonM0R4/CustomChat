package com.example.wilson.customchat.home.contacts;

import com.example.wilson.customchat.User;

/**
 * Created by wmora on 9/26/16.
 */

public class Contact {

    //constants
    public static final String CONTACT_KEY_NAME = User.USERNAME;
    public static final String CONTACT_KEY_AVAILABILITY = User.USER_AVALIABILITY;
    public static final String CONTACT_KEY_STATE = User.USER_STATE;
    public static final String CONTACT_KEY_EMAIL = User.EMAIL_KEY;
    public static final String CONTACT_KEY_PROFILE_IMAGE = User.USER_PROFILE_IMAGE;


    //properties
    private String contactUsername;
    private String contactAvailability;
    private String contactState;
    private String contactEmail;
    private String contactProfileImagePath;

    public Contact(){
        //empty constructor
    }


    public String getContactState() {
        return contactState;
    }

    public void setContactState(String contactState) {
        this.contactState = contactState;
    }

    public String getContactAvailability() {
        return contactAvailability;
    }

    public void setContactAvailability(String contactAvailability) {
        this.contactAvailability = contactAvailability;
    }

    public String getContactUsername() {
        return contactUsername;
    }

    public void setContactUsername(String contactUsername) {
        this.contactUsername = contactUsername;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactProfileImagePath() {
        return contactProfileImagePath;
    }

    public void setContactProfileImagePath(String contactProfileImagePath) {
        this.contactProfileImagePath = contactProfileImagePath;
    }
}
