package com.example.wilson.customchat.home.contacts;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wilson.customchat.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wmora on 9/29/16.
 */

public class ContactDialog extends DialogFragment {

    @Bind(R.id.button_add) Button btnAdd;
    @Bind(R.id.button_cancel) Button btnCancel;
    @Bind(R.id.text_username) TextView textUsername;

    private static final String TAG = "ContactsView";

    private View contactView;
    private ContactsController controller;
    private Contact contact;


    public ContactDialog newInstance(ContactsController controller, Contact contact){
        this.controller = controller;
        this.contact = contact;
        return this;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        super.onCreateDialog(savedInstanceState);

        contactView = LayoutInflater.from(getActivity()).inflate(R.layout.contact_dialog,null);

        if(contactView!=null){
            ButterKnife.bind(this,contactView);
        }

        AlertDialog dialog = new AlertDialog.Builder(getActivity()).setView(contactView).create();
        return dialog;
    }

    @Override
    public void onStart(){
        super.onStart();
        textUsername.setText(contact.getContactUsername());
    }

    @OnClick(R.id.button_add)
    public void onAddPressed(){
        Log.d(TAG,"add pressed");
        if(contact!=null && controller!=null){
            controller.addContact(contact.getContactEmail());
        }else{
            Log.e(TAG,"cannot load the data");
        }
    }

    @OnClick(R.id.button_cancel)
    public void onCancelPressed(){
        Log.d(TAG,"cancel pressed");
        this.dismiss();
    }

}
