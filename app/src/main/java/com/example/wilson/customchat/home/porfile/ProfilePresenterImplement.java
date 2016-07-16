package com.example.wilson.customchat.home.porfile;

/**
 * Created by wilson on 15/07/2016.
 */
public class ProfilePresenterImplement implements ProfilePresenter {

    ProfileInteractor interactor;

    public ProfilePresenterImplement(){
        interactor = new ProfileInteractorImplement();
    }

    @Override
    public void signOff() {
        if(interactor!=null)
            interactor.signOff();
    }
}
