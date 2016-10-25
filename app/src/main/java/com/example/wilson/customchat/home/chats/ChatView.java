package com.example.wilson.customchat.home.chats;

import java.util.ArrayList;

/**
 * Created by wmora on 10/13/16.
 */

public interface ChatView {

    void newMessage();
    void deleteMessage();
    void showChats(ArrayList<Chat> chats);
    //void showChat(ArrayList<Message> messages);

}
