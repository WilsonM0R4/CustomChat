package com.example.wilson.customchat.home.chats;

import java.util.ArrayList;

/**
 * Created by wmora on 10/13/16.
 */

public class ChatControllerImplementation implements ChatController {

    protected ChatRepository repository;

    @Override
    public void onCreate() {
        repository = new ChatRepository(this);
        repository.initListeners();
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
}
