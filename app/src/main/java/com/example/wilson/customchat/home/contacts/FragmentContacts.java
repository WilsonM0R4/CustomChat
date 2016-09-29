package com.example.wilson.customchat.home.contacts;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wilson.customchat.R;
import com.example.wilson.customchat.User;
import com.example.wilson.customchat.commons.ContactRecViewAdapter;
import com.example.wilson.customchat.commons.ViewHelper;
import com.example.wilson.customchat.home.HomeActivity;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wmora on 7/15/16.
 */
public class FragmentContacts extends Fragment implements ContactsView, ViewHelper{

    @Bind(R.id.contactsList) RecyclerView contactsList;
    @Bind(R.id.noContactsText) TextView textNoContacts;
    @Bind(R.id.fabAddContact) FloatingActionButton fabAddContacts;

    private static final String TAG = "ContactsView";

    View contactsView;
    HomeActivity activity;
    ContactsController controller;
    ContactRecViewAdapter adapter;
    RecyclerView.LayoutManager manager;

    ProgressDialog dialog;

    public FragmentContacts newInstance(HomeActivity activity){
        FragmentContacts fragment = new FragmentContacts();
        this.activity = activity;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        contactsView = inflater.inflate(R.layout.fragment_contacts,container,false);
        ButterKnife.bind(this,contactsView);
        controller = new ContactsControllerImplementation();
        controller.setView(this);
        controller.setViewActivity(activity);
        controller.loadListeners();
        return contactsView;
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

    }

    @OnClick(R.id.fabAddContact)
    public void searchContacts(){
        //SearchContactDialog dialog = new SearchContactDialog();
        ContactDialog dialog = new ContactDialog();
        dialog.newInstance(controller);
        dialog.show(getFragmentManager(),"tagSearchUser");
    }



    @Override
    public void getContacts(ArrayList<String> receivedContacts) {

    }

    @Override
    public void showContacts(ArrayList<Contact> userContacts) {

        Log.d(TAG,"me has llamado!");
        manager = new LinearLayoutManager(activity);
        //ArrayList<HashMap<String,String>> items = new ArrayList<>();

        if(userContacts!=null && !userContacts.isEmpty()){
            adapter = new ContactRecViewAdapter(userContacts);
            contactsList.setLayoutManager(manager);
            contactsList.setAdapter(adapter);
            contactsList.setVisibility(View.VISIBLE);
            textNoContacts.setVisibility(View.GONE);
            Log.d(TAG,"data sent to Rec");
        }else{
            Log.e(TAG,"didn't receive data, for that we cannot display the list");
        }

    }

    @Override
    public void showProgressDialog() {

        if(activity!=null){
            dialog = new ProgressDialog(activity);
            dialog.setIndeterminate(true);
            dialog.setTitle(getString(R.string.loading_string));
            dialog.setMessage(getString(R.string.loading_contacts_message));
            dialog.show();
        }else{
            Log.e("ContactsView","cannot load the dialog");
        }


    }

    @Override
    public void hideProgressDialog() {
        if(dialog!=null){
            dialog.hide();
            dialog.dismiss();
        }else{
            Log.e("ContactsView","cannot hide or dismiss the dialog");
        }
    }

    @Override
    public void enableInputs() {
        //not used for now
    }

    @Override
    public void disableInputs() {
        //not used for now
    }




    private void configContactList(){
        /*RecyclerView.LayoutManager manager = new LinearLayoutManager(activity);
        ArrayList<HashMap<String,String>> items = new ArrayList<>();
        HashMap<String,String> item1 = new HashMap<>();
        item1.put(User.USER_PROFILE_IMAGE,"pacho");
        item1.put(User.EMAIL_KEY,"pacho");
        item1.put(User.USER_STATE,"state of pacho");
        HashMap<String,String> item2 = new HashMap<>();
        item2.put(User.USER_PROFILE_IMAGE,"pacho");
        item2.put(User.EMAIL_KEY,"pepe");
        item2.put(User.USER_STATE,"state of pepe");
        HashMap<String,String> item3 = new HashMap<>();
        item3.put(User.USER_PROFILE_IMAGE,"pacho");
        item3.put(User.EMAIL_KEY,"pablo");
        item3.put(User.USER_STATE,"state of pablo");
        items.add(0,item1);
        items.add(1,item2);
        items.add(2,item3);

        ArrayList<String> keys = new ArrayList<>();
        keys.add(0,User.USER_PROFILE_IMAGE);
        keys.add(1,User.EMAIL_KEY);
        keys.add(2,User.USER_STATE);

        adapter = new ContactRecViewAdapter(items);

        contactsList.setLayoutManager(manager);
        contactsList.setAdapter(adapter);
        contactsList.setVisibility(View.VISIBLE);
        textNoContacts.setVisibility(View.GONE);*/

    }

}
