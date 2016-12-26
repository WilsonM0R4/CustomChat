package com.example.wilson.customchat.home.contacts;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wilson.customchat.R;
import com.example.wilson.customchat.commons.MessageDialog;
import com.example.wilson.customchat.commons.ViewHelper;
import com.example.wilson.customchat.home.HomeActivity;
import com.example.wilson.customchat.home.chats.dialog.ChatDialog;

import java.util.ArrayList;

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
    @Bind(R.id.coordinator_container) View coordinatorContainer;

    private static final String TAG = "ContactsView";

    private ArrayList<Contact> userList;
    private View itemView;

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
        controller.setFragment(this);
        controller.loadListeners();
        controller.setCoordinatorView(coordinatorContainer);
        registerForContextMenu(contactsList);

        return contactsView;
    }

    /*** Context menu methods ***/
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo info){
        super.onCreateContextMenu(menu, view, info);

        MenuInflater menuInflater = getActivity().getMenuInflater(); //activity.getMenuInflater();
        menuInflater.inflate(R.menu.context_menu_contact, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item){

            switch(item.getItemId()){
                case R.id.item_view:

                    ContactDialog dialog = new ContactDialog();
                    dialog.newInstance(controller,userList.get(contactsList.getChildAdapterPosition(itemView)),ContactDialog.TYPE_SHOW);
                    dialog.show(getFragmentManager(),"TagShowContact");

                    break;
                case R.id.item_new_message:

                    ChatDialog chatDialog = new ChatDialog();
                    chatDialog.newInstance(userList.get(contactsList.getChildAdapterPosition(itemView)),null);
                    chatDialog.show(getFragmentManager(),"TagChatDialog");

                    break;
                case R.id.item_delete:
                    final MessageDialog messageDialog = new MessageDialog();
                    messageDialog.newInstance(getActivity(),getString(R.string.delete_title),getString(R.string.delete_message));
                    messageDialog.setOnAcceptClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d(TAG,"accept pressed");
                            controller.deleteContact(userList.get(contactsList.getChildAdapterPosition(itemView)).getContactEmail());
                            messageDialog.dismiss();
                        }
                    });

                    messageDialog.setOnCancelClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d(TAG,"cancel pressed");
                            messageDialog.dismiss();
                        }
                    });
                    messageDialog.show(getFragmentManager(),"messageTag");
                    break;
            }

        return  true;
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
        SearchContactDialog dialog = new SearchContactDialog();
        dialog.newInstance(controller);
        dialog.show(getFragmentManager(),SearchContactDialog.DIALOG_TAG);
    }

    @Override
    public void getContacts(ArrayList<String> receivedContacts) {
        //unused method
    }

    @Override
    public void showContacts(final ArrayList<Contact> userContacts) {

        Log.d(TAG,"me has llamado!");
        manager = new LinearLayoutManager(activity);

        if(userContacts!=null && !userContacts.isEmpty()){
            adapter = new ContactRecViewAdapter(userContacts);
            userList = userContacts;

            /*** onClickListener for each item ***/
            adapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(TAG,"View have been clicked");

                    itemView = v;
                    getActivity().openContextMenu(v);
                    /*ContactDialog dialog = new ContactDialog();
                    dialog.newInstance(controller,userContacts.get(contactsList.getChildAdapterPosition(v)),ContactDialog.TYPE_SHOW);
                    dialog.show(getFragmentManager(),"TagShowContact");*/
                }
            });

            /*** onLongClickListener for each item ***/
            /*adapter.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //itemView = v;
                    //getActivity().openContextMenu(v);
                    return true;
                }
            });*/
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



}
