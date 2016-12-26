package com.example.wilson.customchat.home.chats.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wilson.customchat.R;
import com.example.wilson.customchat.commons.DateHelper;
import com.example.wilson.customchat.home.chats.Chat;
import com.example.wilson.customchat.home.chats.ChatController;
import com.example.wilson.customchat.home.chats.ChatControllerImplementation;
import com.example.wilson.customchat.home.chats.Message;
import com.example.wilson.customchat.home.contacts.Contact;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wmora on 12/26/16.
 */

public class ChatDialog extends DialogFragment {

    //butterknife binds
    @Bind(R.id.contactNameText) public TextView contactName;
    @Bind(R.id.contactLastMessage) public TextView lastMessage;
    @Bind(R.id.newMessageET) public EditText messageEditText;

    private View chatDialogView;
    private Contact contact;
    private String lastMessageString;
    private ChatController controller;

    public ChatDialog newInstance(Contact contact, String lastMessage){
        this.contact = contact;
        this.lastMessageString = lastMessage;

        this.controller = new ChatControllerImplementation();

        return this;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        chatDialogView = LayoutInflater.from(getActivity().getBaseContext()).inflate(R.layout.chat_dialog,null);

        if(chatDialogView!=null){
            ButterKnife.bind(this,chatDialogView);
        }

        return new AlertDialog.Builder(getActivity()).setView(chatDialogView).create();
    }

    @Override
    public void onStart(){
        super.onStart();

        if(lastMessageString==null){
            lastMessage.setVisibility(View.GONE);
        }else{
            lastMessage.setText(lastMessageString);
        }

        contactName.setText(contact.getContactUsername());

        Log.e("Dialog","the contact is: "+contact.getContactUsername());

    }

    @OnClick(R.id.sendMessageButton)
    public void sendMessage(){

        Message message = new Message();

        message.setContent(messageEditText.getText().toString());
        message.setDate(DateHelper.getCurrentDate());
        message.setDeliver(controller.getCurrentUser());
        message.setHour(DateHelper.getCurrentHour());

        controller.sendMessage(message,
                Chat.createChatPath(controller.getCurrentUser(), contact.getContactEmail()),
                DateHelper.replaceCharactersInDate(DateHelper.getExactCurrentDate(),"_"));


        this.dismiss();
    }

}
