package com.example.wilson.customchat.home.contacts;

import java.util.Map;

/**
 * Created by wmora on 9/6/16.
 */
public interface ContactsController {

    void loadContacts(String user);
    void addContact();
    void searchContacts(String email);
    void updateContact();
    void deleteContact();
    Map<String,Object> onContactFound(Map<String,Object> contactInfo);
    String onContactNotFound(String message);
    void onDestroy();
}
