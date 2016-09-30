package com.example.wilson.customchat.commons;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.wilson.customchat.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wmora on 9/29/16.
 */

public class MessageDialog extends DialogFragment {

    @Bind(R.id.dialog_title) TextView dialogTitle;
    @Bind(R.id.dialog_message) TextView dialogMessage;

    private View view;
    private Activity activity;
    private String message, title;

    public MessageDialog newInstance(Activity activity,String title, String message){
        this.activity = activity;
        this.title = title;
        this.message = message;
        return this;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        super.onCreateDialog(savedInstanceState);

        view = LayoutInflater.from(getActivity()).inflate(R.layout.message_dialog,null);

        if(view!=null){
            ButterKnife.bind(this,view);
        }

        return new AlertDialog.Builder(getActivity()).setView(view).create();
    }

    @Override
    public void onStart(){
        super.onStart();
        //if(dialogTitle!=null && dialogMessage!=null){
            dialogTitle.setText(title);
            dialogMessage.setText(message);
        //}

    }

    @OnClick(R.id.button_accept)
    public void onAcceptPressed(){
        this.dismiss();
    }
}
