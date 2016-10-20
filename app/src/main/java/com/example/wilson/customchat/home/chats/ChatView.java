package com.example.wilson.customchat.home.chats;

import java.util.ArrayList;

/**
 * Created by wmora on 10/13/16.
 */

public interface ChatView {

    void newMessage();
    void deleteMessage();
    void showContacts(ArrayList<Chat> chats);

}
