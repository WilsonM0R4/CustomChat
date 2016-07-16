package com.example.wilson.customchat.home.porfile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wilson.customchat.R;
import com.example.wilson.customchat.home.HomeActivity;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wmora on 7/15/16.
 */
public class FragmentPorfile extends Fragment implements ProfileView{

    ProfilePresenter presenter ;
    HomeActivity activity;

    public FragmentPorfile newInstance(HomeActivity activity){
        this.activity = activity;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View homeRootView =  inflater.inflate(R.layout.fragment_porfile,container,false);
        ButterKnife.bind(homeRootView);
        presenter = new ProfilePresenterImplement();

        return homeRootView;
    }

    @OnClick(R.id.btnSignOff)
    @Override
    public void signOff() {
        if(presenter==null){
            presenter = new ProfilePresenterImplement();
        }
        presenter.signOff();
        activity.destroy();

    }
}
