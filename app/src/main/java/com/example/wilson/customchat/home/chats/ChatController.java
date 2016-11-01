package com.example.wilson.customchat.home.chats;

import android.app.Activity;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by wmora on 10/13/16.
 */

public interface ChatController {

    void onCreate();
    void setView(ChatView view);
    void setChatActivity(ChatActivity activity);
    void setBaseActivity(Activity activity);
    void setFragment(Fragment fragment);
    void newMessage();
    void deleteMessage();
    ArrayList<Chat> getChats();
    void listChats(ArrayList<Chat> chats);
    void showChat(Map<String, Map<String, String>> messages);
    void getMessages(String chatPath);
    void sendMessage(Message message, String chatPath, String messageKey);


}
