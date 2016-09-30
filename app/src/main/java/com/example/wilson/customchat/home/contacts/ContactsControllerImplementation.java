package com.example.wilson.customchat.home.contacts;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;

import com.example.wilson.customchat.R;
import com.example.wilson.customchat.User;
import com.example.wilson.customchat.commons.MessageDialog;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by wmora on 9/6/16.
 */
public class ContactsControllerImplementation implements ContactsController {

    private static final String TAG = "ContactsController";

    private ContactsRepository repository;
    private ContactsView view;
    private FragmentContacts fragment;
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
    public void setFragment(FragmentContacts fragment) {
        this.fragment = fragment;
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
    public void addContact(String email) {
        if(email!=null && !email.isEmpty()){
            repository.addContact(email);
        }
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
            ContactDialog dialog = new ContactDialog();
            dialog.newInstance(this, contactList.get(0));
            dialog.show(fragment.getFragmentManager(),"TagContactDialog");
        }else{
            Log.e("ContactsContoller","no se ha obtenido informacion");
        }
    }

    @Override
    public String onContactNotFound() {

        if(activity!=null){
            MessageDialog dialog = new MessageDialog();
            dialog.newInstance(activity,"Ups!",activity.getString(R.string.contact_not_found_message));
            dialog.show(activity.getFragmentManager(),"TagMessageDialog");
        }

        return null;
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
