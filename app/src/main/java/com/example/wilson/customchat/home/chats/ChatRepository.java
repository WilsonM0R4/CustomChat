package com.example.wilson.customchat.home.chats;

import com.example.wilson.customchat.domain.FirebaseHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by wmora on 10/13/16.
 */

public class ChatRepository {

    private FirebaseHelper helper;
    private ChatController controller;

    public ChatRepository(ChatController controller){
        this.controller = controller;
        helper = FirebaseHelper.getInstance();
    }

    protected void initListeners(){
        helper.getDatabaseReference().child(FirebaseHelper.CHATS_PATH).addValueEventListener(chatListener());
    }

    private ValueEventListener chatListener(){
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }

}
