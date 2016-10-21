package com.example.wilson.customchat.home.chats;

import android.util.Log;

import com.example.wilson.customchat.User;
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

                switch(type){
                    case TYPE_MAIN_DATA:
                        getChats(dataSnapshot);
                        break;
                    case TYPE_SPECIAL_DATA:
                        //getMessageData(dataSnapshot);
                        break;
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG,"database error: "+databaseError.getMessage());
            }
        };
    }

    private ArrayList<Chat> getChats(DataSnapshot dataSnapshot){

        /*GenericTypeIndicator<Map<String,Map<String,String>>> indicator = new GenericTypeIndicator<Map<String, Map<String, String>>>() {};
        Map<String,Map<String,String>> chatsData = dataSnapshot.getValue(indicator);*/



        GenericTypeIndicator <Map<String,Map<String,Object>>> indicator = new GenericTypeIndicator<Map<String,Map<String,Object>>>() {};
        Map <String,Map<String,Object>> data = dataSnapshot.getValue(indicator);
        chats = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<Chat> chats = new ArrayList<>();

        if(data!=null){
            temp.addAll(data.keySet());
            Log.d(TAG,"response here is "+data);
            for(int c =0;c<temp.size();c++){

                Chat chat = new Chat();

                if(User.formatEmail(temp.get(c)).contains(formattedUserEmail)){
                    Log.d(TAG,"contains the value, it is "+formattedUserEmail);
                    String key = User.formatEmail(temp.get(c));
                    ArrayList<String> tempArray = new ArrayList<>();
                    tempArray.addAll(data.get(key).keySet());

                    ArrayList<Message> messages = new ArrayList<>();

                    int index = tempArray.size();

                    for(int count=0; count<index; count++){
                        Log.d(TAG,"key is "+key);
                        chat.setLastMessageHour(data.get(key).get(Chat.CONTENT_PATH).toString());
                        Message message = new Message();
                        message.setContent(data.get(key).get(Chat.CONTENT_PATH).toString());
                        message.setHour(data.get(key).get(Chat.HOUR_PATH).toString());
                        message.setDeliver(data.get(key).get(Chat.SENDER_PATH).toString());
                        message.setDate(data.get(key).get(Chat.DATE_PATH).toString());

                        messages.add(message);
                        Log.d(TAG,"content here is "+message.getContent());
                    }

                    chat.setMessages(messages);
                    Log.d(TAG,"temp value is "+temp.get(c));
                }else{
                    Log.d(TAG,"don't contains the value");
                }

                chats.add(chat);
            }

        }

        return chats;
    }

    private void getMessageData(DataSnapshot dataSnapshot) {
        GenericTypeIndicator<Map<String, Map<String, String>>> response = new GenericTypeIndicator<Map<String, Map<String, String>>>() {};
        Map<String, Map<String, String>> data; //= new HashMap<>();
        ArrayList<String> keys = new ArrayList<>();
        ArrayList<Message> messages = new ArrayList<>();
        Chat chat = new Chat();
        Message message = new Message();

        data = dataSnapshot.getValue(response);
        if (data != null) {

            /**
             response
             * Chat information
             * **/
            /*data.put(Chat.CONTENT_PATH, (responseValue.get(Chat.CONTENT_PATH)).toString());
            data.put(Chat.DATE_PATH, (responseValue.get(Chat.DATE_PATH)).toString());
            data.put(Chat.HOUR_PATH, (responseValue.get(Chat.HOUR_PATH)).toString());
            data.put(Chat.SENDER_PATH, (responseValue.get(Chat.SENDER_PATH).toString()));*/

            Log.d(TAG,"data here is "+data);
            keys.addAll(data.keySet());

            for(int c=0; c<keys.size();c++){
                Log.d(TAG,"value is "+data.get(keys.get(c)).get(Chat.CONTENT_PATH));
                message.setContent(data.get(keys.get(c)).get(Chat.CONTENT_PATH));
                message.setDate(data.get(keys.get(c)).get(Chat.DATE_PATH));
                message.setDeliver(data.get(keys.get(c)).get(Chat.SENDER_PATH));
                message.setHour(data.get(keys.get(c)).get(Chat.HOUR_PATH));

                messages.add(message);
            }

            chat.setMessages(messages);
            chat.setLastMessageContent(messages.get(0).getContent());
            chat.setLastMessageDate(messages.get(0).getDate());
            chat.setLastMessageHour(messages.get(0).getHour());


        }else{
            Log.e(TAG,"received data is null");
        }

    }

}
