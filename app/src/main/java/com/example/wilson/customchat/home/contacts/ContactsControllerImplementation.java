package com.example.wilson.customchat.home.contacts;

import android.app.Activity;
import android.util.Log;

import com.example.wilson.customchat.User;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by wmora on 9/6/16.
 */
public class ContactsControllerImplementation implements ContactsController {

    private static final String TAG = "ContactsController";

    private ContactsRepository repository;
    private ContactsView view;
    private Activity activity;

    public ContactsControllerImplementation(){
        repository = new ContactsRepository(this);

        Log.e(TAG,"path is "+repository.contactsPath);
    }

    @Override
    public void setView(ContactsView view) {
        this.view = view;
    }

    @Override
    public void setViewActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void loadContacts(ArrayList<Contact> userContacts) {
        if(view!=null){
            Log.d(TAG,"i have the view");

            view.showContacts(userContacts);
        }else{
            Log.e(TAG,"Cannot show received data");
        }
    }


    @Override
    public void loadListeners() {
        if(repository!=null){
            repository.launchListeners();
        }else{
            Log.e(TAG,"cannot launch listeners");
        }

    }

    @Override
    public void addContact() {

    }

    @Override
    public void searchContacts(String email) {

        repository.searchContact(email);

    }

    @Override
    public void updateContact() {

    }

    @Override
    public void deleteContact() {

    }

    @Override
    public void onContactFound(ArrayList<Contact> contactList) {
        Log.d(TAG,"you've called me!");
        if(contactList!=null){
            Log.d("ContactsController","se ha obtenido la informacion: "+contactList.get(0).getContactUsername());

        }else{
            Log.e("ContactsContoller","no se ha obtenido informacion");
        }
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

    private ContactsRepository instantiateRepository(){
        return new ContactsRepository(this);
    }
}
