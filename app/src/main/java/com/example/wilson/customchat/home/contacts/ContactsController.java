package com.example.wilson.customchat.home.contacts;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by wmora on 9/6/16.
 */
public interface ContactsController {

    void setView(ContactsView view);
    void loadContacts(ArrayList<Contact> userContacts); //ArrayList<String> userContacts
    void loadValueEventListener();
    void addContact();
    void searchContacts(String email);
    void updateContact();
    void deleteContact();
    Map<String,String> onContactFound(Map<String,String> contactInfo);
    String onContactNotFound(String message);
    void onDestroy();
}
