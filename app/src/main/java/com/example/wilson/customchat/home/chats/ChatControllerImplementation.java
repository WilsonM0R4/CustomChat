package com.example.wilson.customchat.home.chats;

import android.util.Log;

import com.example.wilson.customchat.commons.ShareDataHelper;

import java.util.ArrayList;

/**
 * Created by wmora on 10/13/16.
 */

public class ChatControllerImplementation implements ChatController {

    protected ChatRepository repository;
    private ChatView view;
    private ChatActivity activity;

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
    public void showChat(ArrayList<Message> messages) {
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
}
