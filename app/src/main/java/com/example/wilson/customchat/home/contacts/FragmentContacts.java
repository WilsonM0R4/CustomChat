package com.example.wilson.customchat.home.contacts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wilson.customchat.R;

/**
 * Created by wmora on 7/15/16.
 */
public class FragmentContacts extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View contactsView =  inflater.inflate(R.layout.fragment_contacts,container,false);

        return contactsView;
    }

}
