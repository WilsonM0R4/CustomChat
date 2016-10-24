package com.example.wilson.customchat.home.chats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.wilson.customchat.R;
import com.example.wilson.customchat.User;
import com.example.wilson.customchat.commons.DateHelper;
import com.example.wilson.customchat.commons.ShareDataHelper;
import com.example.wilson.customchat.domain.FirebaseHelper;
import com.firebase.client.Firebase;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.chat_list) RecyclerView chatList;
    @Bind(R.id.etTypeMessage) EditText etTypeMessage;

    private Chat chat;
    private static final String TAG = "ChatActivity";
    private ArrayList<Message> messages;
    private ChatController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        chat = ShareDataHelper.getInstance().getChat();
        controller = ShareDataHelper.getInstance().getController();
        /**
         * including the custom navbar
         * */
        //toolbar = (Toolbar)findViewById();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp);
        toolbar.setTitle(chat.getLastMessageSender());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissView();
                Log.d(TAG,"view dismissed");
            }
        });

        showChat(chat.getMessages());

    }

    public void showChat(ArrayList<Message> messages){

        ChatRecViewAdapter adapter = new ChatRecViewAdapter(messages);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);

        chatList.setLayoutManager(manager);
        chatList.setAdapter(adapter);

    }

    @OnClick(R.id.btnSend)
    public void sendMessage(){
        Message message = new Message();

        message.setDate(DateHelper.getCurrentDate());
        message.setHour(DateHelper.getCurrentHour());
        message.setContent(etTypeMessage.getText().toString());
        message.setDeliver(User.formatEmail(FirebaseHelper.getInstance().getCurrentUserReference().getEmail()));

        /// REVIEW THIS !!!
        controller.sendMessage(message);
    }

    private void dismissView(){
        this.finish();
    }
}
