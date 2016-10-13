package com.example.wilson.customchat.home.chats;

import java.util.ArrayList;

/**
 * Created by wmora on 10/12/16.
 */

public class Message {

    private String date;
    private  String hour;
    private  ArrayList<String> users;
    private  String deliver;
    private  String content;

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

    /***public String getDeliver(){

    }*/
}
