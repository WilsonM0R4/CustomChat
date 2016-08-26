package com.example.wilson.customchat.home.porfile;

import android.util.Log;

import com.example.wilson.customchat.R;
import com.example.wilson.customchat.User;
import com.example.wilson.customchat.home.porfile.events.ProfileEvents;
import com.example.wilson.customchat.lib.EventBus;
import com.example.wilson.customchat.lib.GreenRobotEventBus;

import java.util.Map;


/**
 * Created by wilson on 15/07/2016.
 */
public class ProfilePresenterImplement implements ProfilePresenter {

    ProfileInteractor interactor;
    FragmentPorfile profile;
    Map userData;
    EventBus eventBus;

    public ProfilePresenterImplement(FragmentPorfile profile){
        interactor = new ProfileInteractorImplement();
        this.profile = profile;
        userData = User.getInstance().getUserData();
        eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }

    @Override
    public void setUserDataToView() {
        profile.textUsername.setText(getUsername());
        profile.userEmail.setText(getUserEmail());
        profile.userState.setText(profile.activity.getResources().getString(R.string.state_text)+getActualState());
    }

    @Override
    public String getUsername() {
        return interactor.getUsername();
    }

    @Override
    public String getUserEmail() {
        return interactor.getUserEmail();
    }

    @Override
    public void changeState(String state) {
        interactor.changeState(state);
    }

    @Override
    public void changeUsername(String newUsername) {
        interactor.changeUsername(newUsername);
    }

    @Override
    public String getActualState() {
        Log.e("status","actual status is "+interactor.getActualStatus());
        return interactor.getActualStatus();
    }

    @Override
    public void setUserStatusToView() {
        String message = profile.activity.getResources().getString(R.string.state_text);
        profile.updateStateInView(message.concat(" "+interactor.getActualStatus()));
        //profile.userState.setText(message.concat(" "+interactor.getActualStatus()));
    }

    @Override
    public void updateUsername() {

    }

    @Override
    public void onEventMainThread(ProfileEvents event) {

        boolean eventType = event.getEventType();

        if(eventType){
            setUserStatusToView();
        }

    }

    @Override
    public void signOff() {
        interactor.signOff();
    }
}
