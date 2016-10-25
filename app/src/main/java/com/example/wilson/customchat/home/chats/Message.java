package com.example.wilson.customchat.home.chats;

import com.example.wilson.customchat.User;
import com.example.wilson.customchat.commons.DateHelper;

import java.util.ArrayList;

/**
 * Created by wmora on 10/12/16.
 */

public class Message {

    /**
     * Properties for a message
     * **/
    private String date;
    private  String hour;
    private  ArrayList<String> users;
    private  String deliver;
    private  String content;

    /**
     * Getters and setters for a message
     * **/
    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getHour(){
        return hour;
    }

    public void setHour(String hour){
        this.hour = hour;
    }

    public ArrayList<String> getUsers(){
        return users;
    }

    public void setUsers(ArrayList<String> users){
        this.users = users;
    }

    public String getDeliver(){
        return deliver;
    }

    public void setDeliver(String deliver){
        this.deliver = deliver;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    /**
     * Class methods
     * */

    public static String createChatKey(String username){
        return User.formatEmail(username)+ DateHelper.getExactCurrentDate();
    }
}


