package com.example.wilson.customchat.home.contacts;

import android.util.Log;

import java.util.Map;

/**
 * Created by wmora on 9/6/16.
 */
public class ContactsControllerImplementation implements ContactsController {

    private ContactsRepository repository;

    public ContactsControllerImplementation(){
        repository = new ContactsRepository(this);
        Log.e("ContactsController","path is "+repository.contactsPath);
    }

    @Override
    public void loadContacts(String user) {

    }

    @Override
    public void addContact() {

    }

    @Override
    public void searchContacts(String email) {

    }

    @Override
    public void updateContact() {

    }

    @Override
    public void deleteContact() {

    }

    @Override
    public Map<String, Object> onContactFound(Map<String, Object> contactInfo) {
        return contactInfo;
    }

    @Override
    public String onContactNotFound(String message) {
        return message;
    }

    @Override
    public void onDestroy() {
        if(repository!=null){
            repository.onDestroy();
        }
    }
}
