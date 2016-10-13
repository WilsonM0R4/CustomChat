package com.example.wilson.customchat.home.chats;

import java.util.ArrayList;

/**
 * Created by wmora on 10/13/16.
 */

public interface ChatController {

    void newMessage();
    void deleteMessage();
    ArrayList<Chat> getChats();

}
