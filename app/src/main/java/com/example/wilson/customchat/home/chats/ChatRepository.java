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
                        getMessageData(dataSnapshot);
                        break;
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG,"database error: "+databaseError.getMessage());
            }
        };
    }

    private void getChats(DataSnapshot dataSnapshot){
        GenericTypeIndicator<Map<String,Object>> indicator = new GenericTypeIndicator<Map<String, Object>>() {};
        Map<String, Object> data = dataSnapshot.getValue(indicator);
        chats = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();

        if(data!=null){
            temp.addAll(data.keySet());
            Log.d(TAG,"response here is "+data);
            for(int c =0;c<temp.size();c++){

                if(User.formatEmail(temp.get(c)).contains(formattedUserEmail)){
                    Log.d(TAG,"contains the value, it is "+formattedUserEmail);


                    /***
                     * FIX THIS!!
                     * * **/
                    Log.d(TAG,"temp value is "+temp.get(c));

                    helper.getDatabaseReference()
                            .child(FirebaseHelper.CHATS_PATH)
                            .child(temp.get(c))
                            .addValueEventListener(chatListener(TYPE_SPECIAL_DATA));

                }else{
                    Log.d(TAG,"don't contains the value");
                }
            }
        }
    }

    private void getMessageData(DataSnapshot dataSnapshot) {
        GenericTypeIndicator<Map<String, Map<String, String>>> response = new GenericTypeIndicator<Map<String, Map<String, String>>>() {};
        Map<String, Map<String, String>> data; //= new HashMap<>();
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
            Log.d(TAG,"value is "+data.get(formattedUserEmail).get(Chat.CONTENT_PATH));

            message.setContent(data.get(formattedUserEmail).get(Chat.CONTENT_PATH));
            /*message.setDeliver(responseValue.get(Chat.SENDER_PATH).toString());
            message.setHour(responseValue.get(Chat.HOUR_PATH).toString());
            message.setDate(responseValue.get(Chat.DATE_PATH).toString());*/



        }
        //Log.d(TAG, "response is " + responseValue);
    }

}
