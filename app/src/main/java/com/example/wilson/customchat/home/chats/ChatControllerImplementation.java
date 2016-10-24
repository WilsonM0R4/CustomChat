package com.example.wilson.customchat.home.chats;

import com.example.wilson.customchat.commons.ShareDataHelper;

import java.util.ArrayList;

/**
 * Created by wmora on 10/13/16.
 */

public class ChatControllerImplementation implements ChatController {

    protected ChatRepository repository;
    private ChatView view;

    @Override
    public void onCreate() {
        repository = new ChatRepository(this);
        repository.initListeners();
        ShareDataHelper.getInstance().setController(this);
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
        view.showChats(chats);
    }

    @Override
    public Chat getMessages() {
        return null;
    }

    @Override
    public void sendMessage(Message message, String chatPath, String messageKey) {
        repository.sendMessage(message,chatPath,messageKey);
    }
}
