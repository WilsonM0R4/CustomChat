package com.example.wilson.customchat.home.contacts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wilson.customchat.R;
import com.example.wilson.customchat.User;
import com.example.wilson.customchat.commons.RecyclerViewAdapter;
import com.example.wilson.customchat.home.HomeActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wmora on 7/15/16.
 */
public class FragmentContacts extends Fragment{

    @Bind(R.id.contactsList) RecyclerView contactsList;
    @Bind(R.id.noContactsText) TextView textNoContacts;
    View contactsView;
    HomeActivity activity;
    RecyclerViewAdapter adapter;

    public FragmentContacts newInstance(HomeActivity activity){
        FragmentContacts fragment = new FragmentContacts();
        this.activity = activity;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        contactsView = inflater.inflate(R.layout.fragment_contacts,container,false);
        ButterKnife.bind(this,contactsView);

        configContactList();



        return contactsView;
    }

    private void configContactList(){
        RecyclerView.LayoutManager manager = new LinearLayoutManager(activity);
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

        adapter = new RecyclerViewAdapter(items,keys);

        contactsList.setLayoutManager(manager);
        contactsList.setAdapter(adapter);
        contactsList.setVisibility(View.VISIBLE);
        textNoContacts.setVisibility(View.GONE);

    }

}
