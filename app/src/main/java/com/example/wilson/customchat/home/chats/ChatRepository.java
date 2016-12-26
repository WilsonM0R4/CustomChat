package com.example.wilson.customchat.home.chats;

import android.util.Log;

import com.example.wilson.customchat.User;
import com.example.wilson.customchat.commons.ArrayManager;
import com.example.wilson.customchat.domain.FirebaseHelper;
import com.google.firebase.auth.api.model.StringList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by wmora on 10/13/16.
 */

public class ChatRepository {

    private static final String TAG = "ChatRepository";
    private static final int TYPE_MAIN_DATA = 0;
    private static final int TYPE_SPECIAL_DATA = 1;
    private FirebaseHelper helper;
    private ChatController controller;
    private Map<String, Object> responseValue;
    private ArrayList<Chat> chats;
    private String formattedUserEmail;

    public ChatRepository(ChatController controller) {
        this.controller = controller;
        helper = FirebaseHelper.getInstance();
        formattedUserEmail = User.formatEmail(FirebaseHelper.getInstance().getCurrentUserReference().getEmail());
    }

    protected void initListeners() {
        helper.getDatabaseReference().child(FirebaseHelper.CHATS_PATH).addValueEventListener(chatListener(TYPE_MAIN_DATA));
    }

    private ValueEventListener chatListener(final int type) {
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                switch (type) {
                    case TYPE_MAIN_DATA:
                        controller.listChats(getChats(dataSnapshot));
                        break;
                    case TYPE_SPECIAL_DATA:
                        //getMessageData(dataSnapshot);
                        break;
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "database error: " + databaseError.getMessage());
            }
        };
    }

    private ArrayList<Chat> getChats(DataSnapshot dataSnapshot) {

        GenericTypeIndicator<Map<String, Map<String, Map<String, String>>>> indicator = new GenericTypeIndicator<Map<String, Map<String, Map<String, String>>>>() {
        };
        Map<String, Map<String, Map<String, String>>> data = dataSnapshot.getValue(indicator);
        chats = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<Chat> chats = new ArrayList<>();

        if (data != null) {
            temp.addAll(data.keySet());
            Log.d(TAG, "general response is " + data);
            for (int c = 0; c < temp.size(); c++) {

                Chat chat = null;

                if (User.formatEmail(temp.get(c)).contains(formattedUserEmail)) {
                    Log.d(TAG, "contains the value, it is " + formattedUserEmail);
                    String key = User.formatEmail(temp.get(c));
                    ArrayList<String> tempArray = new ArrayList<>();
                    tempArray.addAll(data.get(key).keySet());

                    ArrayList<Message> messages = new ArrayList<>();

                    /** save the chat path **/
                    chat = new Chat();
                    chat.setChatPath(key,FirebaseHelper.getInstance().getCurrentUserReference().getEmail());

                    int index = tempArray.size();

                    for (int count = 0; count < index; count++) {
                        Log.d(TAG, "key is " + key);
                        Map<String, Map<String, String>> messageMap = data.get(key);
                        Log.d(TAG, "data here in messageMap is " + messageMap);
                        ArrayList<String> messageKey = new ArrayList<>();
                        ArrayList<String> members = new ArrayList<>();
                        messageKey.addAll(messageMap.keySet());

                        for (int l = 0; l < messageKey.size(); l++) {
                            //chat.setLastMessageHour(messageKey.get(l));

                            String messagehour = messageMap.get(messageKey.get(l)).get(Chat.HOUR_PATH);
                            Log.d(TAG, "message hour is " + messagehour);

                            Message message = new Message();
                            message.setContent(messageMap.get(messageKey.get(l)).get(Chat.CONTENT_PATH));
                            message.setHour(messagehour);
                            message.setDeliver(messageMap.get(messageKey.get(l)).get(Chat.SENDER_PATH));
                            message.setDate(messageMap.get(messageKey.get(l)).get(Chat.DATE_PATH));

                            messages.add(message);
                            Log.d(TAG, "content here is " + message.getContent());

                            members.add(message.getDeliver());
                        }

                    }

                    chat.setMessages(messages);
                    chat.setLastMessageHour(messages.get(0).getDate());
                    chat.setLastMessageContent(messages.get(0).getContent());
                    chat.setLastMessageSender(messages.get(0).getDeliver());
                    chat.setChatPath(key,FirebaseHelper.getInstance().getCurrentUserReference().getEmail());

                    Log.d(TAG, "temp value is " + temp.get(c));
                } else {
                    Log.d(TAG, "don't contains the value");
                }

                if(chat!=null){
                    chats.add(chat);
                }

            }

        }

        return chats;
    }

    protected void sendMessage(Message message, String chatPath, String messageKey) {
        Map<String, String> messageData = new HashMap<>();

        messageData.put(Chat.CONTENT_PATH, message.getContent());
        messageData.put(Chat.DATE_PATH, message.getDate());
        messageData.put(Chat.HOUR_PATH, message.getHour());
        messageData.put(Chat.SENDER_PATH, message.getDeliver());

        helper.getDatabaseReference().child(FirebaseHelper.CHATS_PATH).child(chatPath).child(messageKey).setValue(messageData);
    }

    protected void getMessages(String chatPath) {

        if(chatPath!=null && !chatPath.isEmpty()){

            helper.getDatabaseReference().child(FirebaseHelper.CHATS_PATH).child(chatPath).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    //ArrayList<Message> messages = new ArrayList<>();

                    GenericTypeIndicator<Map<String, Map<String, String>>> indicator = new GenericTypeIndicator<Map<String, Map<String, String>>>() {
                    };

                    Map<String, Map<String, String>> messageMap = new TreeMap<>();
                            messageMap.putAll(dataSnapshot.getValue(indicator));

                    if (!messageMap.isEmpty()) {

                        //ArrayList<String> messageKeys = new ArrayList<>();
                        //messageKeys.addAll(messageMap.keySet());

                        //Log.d(TAG,"message here is "+messageMap.get(messageKeys.get));
                        //Log.d(TAG, "message data is " + messageMap);
                        //Log.d(TAG, "message keys are " + messageKeys);

                        /*for (int count = 0; count < messageMap.size(); count++) {
                            Map<String, String> stringMap = messageMap.get(messageKeys.get(count));
                            Log.d(TAG, "string map is " + stringMap);

                            Message message = new Message();

                            message.setContent(stringMap.get(Chat.CONTENT_PATH));
                            message.setDate(stringMap.get(Chat.DATE_PATH));
                            message.setDeliver(stringMap.get(Chat.SENDER_PATH));
                            message.setHour(stringMap.get(Chat.HOUR_PATH));

                            messages.add(count,message);
                            Log.d(TAG, "message is " + message.getContent());

                            Log.d(TAG,"message here is "+messageMap.get(messageKeys.get(count)));

                        }*/
                        controller.showChat(messageMap);
                    } else {
                        Log.e(TAG, "cannot load the messages");
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        } else {
            Log.e(TAG,"chat path is null or empty");
        }

    }


    protected String getCurrentUser(){
        return FirebaseHelper.getInstance().getCurrentUserReference().getEmail();
    }

}
