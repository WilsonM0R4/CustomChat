package com.example.wilson.customchat.home.contacts;

import android.util.Log;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by wmora on 9/6/16.
 */
public class ContactsControllerImplementation implements ContactsController {

    private ContactsRepository repository;
    private ContactsView view;

    public ContactsControllerImplementation(){
        repository = new ContactsRepository(this);
        Log.e("ContactsController","path is "+repository.contactsPath);
    }

    @Override
    public void setView(ContactsView view) {
        this.view = view;
    }

    @Override
    public void loadContacts(ArrayList<Contact> userContacts) {
        if(view!=null){
            Log.d("contactsController","received contacts is: "+userContacts.get(0).getContactUsername());
            view.setContacts(userContacts);
        }else{
            Log.e("ContactsController","cannot load contacts");
        }
    }


    @Override
    public void loadValueEventListener() {
        repository.launchContactReading();
    }

    @Override
    public void addContact() {

    }

    @Override
    public void searchContacts(String email) {
        //loadContacts();
        //repository.getContacts();
        repository.searchRegisteredUser(email);
        Log.e("ContactsController","email for search is "+email);
    }

    @Override
    public void updateContact() {

    }

    @Override
    public void deleteContact() {

    }

    @Override
    public Map<String, String> onContactFound(Map<String, String> contactInfo) {
        if(contactInfo!=null){
            Log.d("ContactsController","se ha obtenido la informacion");
        }else{
            Log.e("ContactsContoller","no se ha obtenido informacion");
        }
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
