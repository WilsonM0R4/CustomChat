package com.example.wilson.customchat.register.events;

/**
 * Created by wmora on 7/14/16.
 */
public class RegisterEvents {
    public static final int onWaitingForResult = 0;
    public static final int onRegisterSuccess = 1;
    public static final int onRegisterError = 2;

    public int eventType;
    public String error;

    public boolean getEventType(){
        boolean result = false;

        switch (eventType){
            case onRegisterError:
            case onWaitingForResult:
                result = false;
            break;
            case onRegisterSuccess:
                result = true;
            break;
        }
        return result;
    }

    public void setEventType(int event){
        this.eventType = event;
    }

    public void setErrorMessage(String error){
        this.error = error;
    }
}
