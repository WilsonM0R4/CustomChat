package com.example.wilson.customchat.home.contacts;

import android.app.Activity;
import android.view.View;

import com.example.wilson.customchat.User;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by wmora on 9/6/16.
 */
public interface ContactsController {

    void setView(ContactsView view);
    void setViewActivity(Activity activity);
    void setFragment(FragmentContacts fragment);
    void setCoordinatorView(View coordinatorView);
    void loadContacts(ArrayList<Contact> userContacts); //ArrayList<String> userContacts
    void loadListeners();
    void addContact(String email);
    void searchContacts(String email);
    void updateContact();
    void deleteContact(String contactEmail);
    void onContactFound(ArrayList<Contact> contactList );
    String onContactNotFound();
    void onContactAdded();
    void onContactDeleted();
    void processFailure(String errorMessage);
    void onDestroy();
}
