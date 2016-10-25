package com.example.wilson.customchat.home.chats;

import android.graphics.Color;

import com.example.wilson.customchat.User;

import java.util.ArrayList;

/**
 * Created by wmora on 10/13/16.
 */

public class Chat {

    public static final String DATE_PATH = "date";
    public static final String HOUR_PATH = "hour";
    public static final String SENDER_PATH = "sender";
    public static final String CONTENT_PATH = "content";
    public static final int COLOR_SENDER = Color.argb(1,255,255,255); //ColorNoBackground
    public static final int COLOR_RECEIVER = Color.argb(1,0,153,153); //ColorAccent

    private ArrayList<Message> messages;
    private String lastMessageContent;
    private String lastMessageHour;
    private String lastMessageDate;
    private String lastMessageSender;
    private String chatPath;

    public ArrayList<Message> getMessages() {
        return messages;
    }
    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public String getLastMessageContent() {
        return lastMessageContent;
    }

    public void setLastMessageContent(String lastMessage) {
        this.lastMessageContent = lastMessage;
    }

    public String getLastMessageHour() {
        return lastMessageHour;
    }

    public void setLastMessageHour(String lastMessageHour) {
        this.lastMessageHour = lastMessageHour;
    }

    public String getLastMessageDate() {
        return lastMessageDate;
    }

    public void setLastMessageDate(String lastMessageDate) {
        this.lastMessageDate = lastMessageDate;
    }

    public String getLastMessageSender() {
        return lastMessageSender;
    }

    public void setLastMessageSender(String lastMessageSender) {
        this.lastMessageSender = lastMessageSender;
    }

    public static String createChatPath(String usermail, String contactmail){
        return User.formatEmail(usermail)+User.formatEmail(contactmail);
    }

    public String getChatPath() {
        return chatPath;
    }

    public void setChatPath(String chatPath) {
        this.chatPath = chatPath;
    }
}
