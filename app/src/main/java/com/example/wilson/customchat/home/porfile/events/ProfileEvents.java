package com.example.wilson.customchat.home.porfile.events;

/**
 * Created by wilson on 17/07/2016.
 */
public class ProfileEvents {

    public static final int onWaitingForResult = 0;
    public static final int onDatabaseError = 1;
    public static final int onDataChanged = 2;

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
