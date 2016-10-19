package com.example.wilson.customchat.home.chats;

import android.util.Log;

import com.example.wilson.customchat.domain.FirebaseHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wmora on 10/13/16.
 */

public class ChatRepository {

    private static final String TAG = "ChatRepository";
    private static final int TYPE_MAIN_DATA = 0;
    private static final int TYPE_USERS = 1;
    private FirebaseHelper helper;
    private ChatController controller;
    private Map<String, Object> responseValue;

    public ChatRepository(ChatController controller) {
        this.controller = controller;
        helper = FirebaseHelper.getInstance();
    }

    protected void initListeners() {
        helper.getDatabaseReference().child(FirebaseHelper.CHATS_PATH).addValueEventListener(chatListener(TYPE_MAIN_DATA));
    }

    private ValueEventListener chatListener(final int type) {
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getMessageData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG,"database error: "+databaseError.getMessage());
            }
        };
    }

    private void getUsersInMessage(DataSnapshot dataSnapshot) {
    }

    private void getMessageData(DataSnapshot dataSnapshot) {
        GenericTypeIndicator<Map<String, Object>> response = new GenericTypeIndicator<Map<String, Object>>() {
        };
        Map<String, String> data = new HashMap<>();

        responseValue = dataSnapshot.getValue(response);
        if (responseValue != null) {

            /**
             * Chat information
             * **/
            data.put(Chat.CONTENT_PATH, (responseValue.get(Chat.CONTENT_PATH)).toString());
            data.put(Chat.DATE_PATH, (responseValue.get(Chat.DATE_PATH)).toString());
            data.put(Chat.HOUR_PATH, (responseValue.get(Chat.HOUR_PATH)).toString());
            data.put(Chat.SENDER_PATH, (responseValue.get(Chat.SENDER_PATH).toString()));


        }
        Log.d(TAG, "response is " + responseValue);
    }

}
