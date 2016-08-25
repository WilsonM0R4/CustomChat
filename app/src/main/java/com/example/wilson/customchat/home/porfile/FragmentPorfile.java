package com.example.wilson.customchat.home.porfile;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wilson.customchat.R;
import com.example.wilson.customchat.home.HomeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wmora on 7/15/16.
 */
public class FragmentPorfile extends Fragment implements ProfileView{

    @Bind(R.id.textUsername) TextView textUsername;
    @Bind(R.id.email) TextView userEmail;
    @Bind(R.id.textCurrentState) TextView userState;
    ProfilePresenter presenter;
    HomeActivity activity;
    StateDialog dialog;
    ProgressDialog progressDialog;

    public FragmentPorfile newInstance(HomeActivity activity){
        this.activity = activity;
        presenter = new ProfilePresenterImplement(this);
        presenter.onCreate();
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view =  inflater.inflate(R.layout.fragment_porfile,container,false);

        ButterKnife.bind(this,view);

        presenter.setUserDataToView();

        return view;
    }

    @Override
    public void onStop(){
        super.onStop();
        presenter.onDestroy();
    }

    @Override
    public void showProgressDialog() {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle(activity.getResources().getString(R.string.loadingTitle));
        progressDialog.setMessage(activity.getResources().getString(R.string.loadingDataMessage));
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
    }

    @Override
    public String getActualState() {
       return presenter.getActualState();
    }

    @Override
    public void updateStateInView(String state) {
        userState.setText(state);
    }

    @OnClick(R.id.lblState)
    @Override
    public void changeState() {
        dialog = new StateDialog();
        dialog.newInstance(activity,this);
        dialog.show (getFragmentManager(),"tagState");

        Log.e("message","new message");
    }

    @Override
    public void onStateDialogFinished(String newState) {
        presenter.changeState(newState);
        Log.e("new state","new state is "+newState);
    }

    @OnClick(R.id.btnSignOff)
    @Override
    public void signOff(){
        presenter.signOff();
        activity.destroy();

    }
}
