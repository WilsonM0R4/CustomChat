package com.example.wilson.customchat.home.chats;

import java.util.ArrayList;

/**
 * Created by wmora on 10/13/16.
 */

public class Chat {

    public static final String DATE_PATH = "date";
    public static final String HOUR_PATH = "hour";
    public static final String SENDER_PATH = "sender";
    public static final String CONTENT_PATH = "content";
    public static final String USERS_path = "users";

    private ArrayList<Message> messages;

    public ArrayList<Message> getMessages() {
        return messages;
    }
    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}
