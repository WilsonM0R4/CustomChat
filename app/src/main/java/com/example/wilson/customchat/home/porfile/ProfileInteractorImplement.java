package com.example.wilson.customchat.home.porfile;

/**
 * Created by wilson on 15/07/2016.
 */
public class ProfileInteractorImplement implements ProfileInteractor {

    ProfileRepository repository;

    public ProfileInteractorImplement(){
        repository = new ProfileRepositoryImplement();
    }

    @Override
    public void signOff() {
        if (repository!=null)
            repository.signOff();
    }
}
