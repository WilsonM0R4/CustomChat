package com.example.wilson.customchat.home.chats;

import java.util.ArrayList;

/**
 * Created by wmora on 10/13/16.
 */

public interface ChatController {

    void onCreate();
    void setView(ChatView view);
    void setChatActivity(ChatActivity activity);
    void newMessage();
    void deleteMessage();
    ArrayList<Chat> getChats();
    void listChats(ArrayList<Chat> chats);
    void showChat(ArrayList<Message> messages);
    void getMessages(String chatPath);
    void sendMessage(Message message, String chatPath, String messageKey);


}
