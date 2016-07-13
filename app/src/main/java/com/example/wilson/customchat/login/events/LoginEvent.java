package com.example.wilson.customchat.login.events;

/**
 * Created by gparrrado on 7/13/16.
 */
public class LoginEvent {

    public static final boolean onSignInError = false;
    public static final boolean onSignInSuccess = true;

    private boolean eventType;
    private String errorMessage;

    public boolean getEventType() {
        return eventType;
    }

    public boolean setEventType(boolean eventType) {
        return this.eventType = eventType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
