package com.example.wilson.customchat.home.porfile;

import android.app.AlertDialog;
import android.app.Dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.wilson.customchat.R;
import com.example.wilson.customchat.home.HomeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wilson on 17/07/2016.
 */
public class StateDialog extends DialogFragment {

    @Bind(R.id.etState) EditText etState;
    View dialogView;
    HomeActivity homeActivity;
    FragmentPorfile fragmentProfile;


    public StateDialog newInstance(HomeActivity activity,FragmentPorfile profile){
        StateDialog dialog = new StateDialog();
        homeActivity = activity;
        fragmentProfile = profile;
        return dialog;
    }

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){

        return dialogView;
    }*/

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.state_dialog,null);
        ButterKnife.bind(this,dialogView);

        return new AlertDialog.Builder(getActivity()).setView(dialogView).create();
    }

    @OnClick(R.id.btnSend)
    void sendState(){
        fragmentProfile.onStateDialogFinished(etState.getText().toString());
        this.dismiss();
    }

    @OnClick(R.id.btnCancel)
    void cancel(){
        this.dismiss();
    }
}
