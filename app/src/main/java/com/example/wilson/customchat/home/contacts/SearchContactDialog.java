package com.example.wilson.customchat.home.contacts;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.wilson.customchat.R;
import com.example.wilson.customchat.User;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wmora on 9/7/16.
 */
public class SearchContactDialog extends DialogFragment {

    @Bind(R.id.btnSearchUser) ImageButton btnSearchUser;
    @Bind(R.id.etSearchUser) EditText etSearchUser;
    private View searchView;
    private View resultsView;
    private Activity activity;
    private ContactsController controller;
    private AlertDialog alertDialog;

    public SearchContactDialog newInstance(ContactsController controller){

        this.controller = controller;
        return this;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        super.onCreateDialog(savedInstanceState);

        activity = getActivity();
        searchView = LayoutInflater.from(activity).inflate(R.layout.search_contact_dialog,null);

        if(searchView!=null){
            ButterKnife.bind(this,searchView);
        }

        alertDialog = new AlertDialog.Builder(activity).setView(searchView).create();
        return alertDialog;
    }

    @OnClick(R.id.btnSearchUser)
    public void onSearchPressed(){

        String email = User.formatEmail(etSearchUser.getText().toString());
        controller.searchContacts(email);

        Log.e("SearchContactDialog","search pressed");
        Toast.makeText(activity.getBaseContext(),"has presionado la lupita de buscar ("+etSearchUser.getText().toString()+")",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btnGoBack)
    public void onBackbuttonPressed(){
        this.dismiss();
    }

}
