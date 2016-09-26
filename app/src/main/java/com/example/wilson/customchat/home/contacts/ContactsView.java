package com.example.wilson.customchat.home.contacts;

import java.util.ArrayList;

/**
 * Created by wmora on 9/6/16.
 */
public interface ContactsView {

    void getContacts(ArrayList<String> receivedContacts);
    void setContacts(ArrayList<Contact> userContacts);
}
