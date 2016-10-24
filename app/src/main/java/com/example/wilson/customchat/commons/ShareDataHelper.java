package com.example.wilson.customchat.commons;

import com.example.wilson.customchat.home.chats.Chat;
import com.example.wilson.customchat.home.chats.ChatController;
import com.example.wilson.customchat.home.chats.ChatView;
import com.example.wilson.customchat.home.chats.Message;

import java.util.ArrayList;

/**
 * Created by wmora on 10/24/16.
 */

public class ShareDataHelper {

    private Chat messages;
    private ChatView view;
    private ChatController controller;

    public ChatView getView() {
        return view;
    }

    public void setView(ChatView view) {
        this.view = view;
    }

    public ChatController getController() {
        return controller;
    }

    public void setController(ChatController controller) {
        this.controller = controller;
    }

    private static class ShareSingleton{
        static final ShareDataHelper INSTANCE = new ShareDataHelper();
    }

    public static ShareDataHelper getInstance(){
        return ShareSingleton.INSTANCE;
    }

    public Chat getChat(){
        return messages;
    }

    public void setChat(Chat messages){
        this.messages = messages;
    }

}
