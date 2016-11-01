package com.example.wilson.customchat.home.chats;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wilson.customchat.R;
import com.example.wilson.customchat.User;
import com.example.wilson.customchat.commons.MessageDialog;
import com.example.wilson.customchat.commons.ShareDataHelper;
import com.example.wilson.customchat.domain.FirebaseHelper;
import com.example.wilson.customchat.home.contacts.Contact;
import com.example.wilson.customchat.home.contacts.ContactRecViewAdapter;
import com.example.wilson.customchat.home.contacts.ContactsRepository;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by wmora on 10/13/16.
 */

public class ChatControllerImplementation implements ChatController {

    protected ChatRepository repository;
    private ChatView view;
    private ChatActivity activity;
    private Activity baseActivity;
    private Fragment fragment;

    @Override
    public void onCreate() {
        repository = new ChatRepository(this);
        repository.initListeners();
        ShareDataHelper.getInstance().setController(this);
    }

    @Override
    public void setChatActivity(ChatActivity activity){
        this.activity = activity;
    }

    @Override
    public void setView(ChatView view) {
        this.view = view;
    }

    @Override
    public void newMessage() {

        final ArrayList<Contact> contacts = FirebaseHelper.getInstance().getFoundContacts();

        for(Contact contact :contacts){
            Log.d("ChatController","contacts are "+contact.getContactEmail());
        }


        final MessageDialog dialog = new MessageDialog();
        dialog.setOnCancelClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        View customView = LayoutInflater.from(baseActivity).inflate(R.layout.contacts_view, null);

        if(customView==null){
            Log.e("ChatController","customView is null");
        }

        final RecyclerView recView = (RecyclerView) customView.findViewById(R.id.contacts_rec_view);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(activity);
        ContactRecViewAdapter adapter = new ContactRecViewAdapter(contacts);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                repository.getMessages(Chat.createChatPath(
                        User.formatEmail(
                                FirebaseHelper.getInstance().getCurrentUserReference().getEmail()),
                        User.formatEmail(
                                contacts.get(recView.getChildAdapterPosition(v)).getContactEmail())));


                baseActivity.startActivity(new Intent(baseActivity.getBaseContext(),ChatActivity.class));
            }
        });

        recView.setAdapter(adapter);
        recView.setLayoutManager(manager);
        dialog.setCustomView(customView);
        dialog.show(fragment.getFragmentManager(),"dialogContacts");
    }

    @Override
    public void deleteMessage() {

    }

    @Override
    public ArrayList<Chat> getChats() {
        return null;
    }

    @Override
    public void listChats(ArrayList<Chat> chats) {
        if(chats!=null && !chats.isEmpty()){
            view.showChats(chats);
        }
    }

    @Override
    public void showChat(Map<String, Map<String, String>> messages) {
        Log.d("View","showing message "+messages);
        activity.showChat(messages);
    }

    @Override
    public void getMessages(String chatPath) {
        repository.getMessages(chatPath);
    }

    @Override
    public void sendMessage(Message message, String chatPath, String messageKey) {
        repository.sendMessage(message,chatPath,messageKey);
    }

    public void setBaseActivity(Activity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
