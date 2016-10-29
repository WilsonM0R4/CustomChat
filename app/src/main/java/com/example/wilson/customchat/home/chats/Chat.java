package com.example.wilson.customchat.home.chats;

import android.graphics.Color;
import android.util.Log;

import com.example.wilson.customchat.User;
import com.example.wilson.customchat.domain.FirebaseHelper;

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
    private String chatTitle;

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

    public static String createChatPath(String userMail, String contactMail){
        return User.formatEmail(userMail)+FirebaseHelper.SEPARATOR+User.formatEmail(contactMail);
    }

    public String getChatPath() {
        return chatPath;
    }

    public void setChatPath(String chatPath, String currentUser) {
        this.chatPath = chatPath;
        findMembersInChat(chatPath,currentUser);
    }

    /**
     * members method
     * */
    private void findMembersInChat(String chatPath, String currentUser){

        int andpersantIndex = chatPath.indexOf("&");
        Log.e("ChatClass","index is "+andpersantIndex);

        String first = chatPath.substring(0,andpersantIndex);
        String second = chatPath.substring(andpersantIndex+1,chatPath.length());

        Log.d("ChatClass","current user is "+currentUser);

        if(User.formatEmail(currentUser).equals(first)){
            chatTitle = second;
        }else{
            chatTitle = first;
        }

        Log.d("ChatClass","first string is "+first+", and second is "+second);
        Log.d("ChatClass","chat title is "+chatTitle);
    }

    public String getChatTitle(){
        return chatTitle;
    }

}
