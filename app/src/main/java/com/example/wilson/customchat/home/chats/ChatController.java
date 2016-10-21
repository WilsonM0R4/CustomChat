package com.example.wilson.customchat.home.chats;

import java.util.ArrayList;

/**
 * Created by wmora on 10/13/16.
 */

public interface ChatController {

    void onCreate();
    void setView(ChatView view);
    void newMessage();
    void deleteMessage();
    ArrayList<Chat> getChats();
    void listChats(ArrayList<Chat> chats);
    Chat getMessages();

}
