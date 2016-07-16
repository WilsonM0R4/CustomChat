package com.example.wilson.customchat.home.porfile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wilson.customchat.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.OnClick;

/**
 * Created by wmora on 7/15/16.
 */
public class FragmentPorfile extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View homeRootView =  inflater.inflate(R.layout.fragment_porfile,container,false);

        return homeRootView;
    }

    @OnClick(R.id.btnSignOff)
    protected void signOff(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signOut();
        //this.finish();
    }

}
