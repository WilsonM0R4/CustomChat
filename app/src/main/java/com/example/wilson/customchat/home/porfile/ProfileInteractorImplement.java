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
    public String getUsername() {
        return repository.getUsername();
    }

    @Override
    public String getUserEmail() {
        return repository.getUserEmail();
    }

    @Override
    public void signOff() {
        if (repository!=null)
            repository.signOff();
    }

    @Override
    public void changeState(String state) {
        repository.changeState(state);
    }

    @Override
    public void changeUsername(String newUsername) {
        repository.changeUsername(newUsername);
    }

    @Override
    public void changeAvailability(String availability) {
        repository.changeAvailability(availability);
    }

    @Override
    public String getActualStatus() {
        return repository.getActualStatus();
    }
}
