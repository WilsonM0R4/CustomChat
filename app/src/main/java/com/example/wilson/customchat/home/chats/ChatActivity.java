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
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends AppCompatActivity implements ChatView{

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.chat_list) RecyclerView chatList;
    @Bind(R.id.etTypeMessage) EditText etTypeMessage;
    @Bind(R.id.type_area) View typeArea;

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


        typeArea.bringToFront();
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

        //showChat(chat.getMessages());
        Log.d(TAG,"path is "+chat.getChatPath());

        controller.setChatActivity(this);
        controller.getMessages(chat.getChatPath());
    }

    @Override
    public void newMessage() {
        /**
         * not used here
         * */
    }

    @Override
    public void deleteMessage() {
        /**
         * not used here
         * */
    }

    @Override
    public void showChats(ArrayList<Chat> chats) {
        /**
         * not used here
         * */
    }

    public void showChat(Map<String, Map<String, String>> messages){

        Log.d(TAG,"i'm here!!");

        ChatRecViewAdapter adapter = new ChatRecViewAdapter(messages);
        LinearLayoutManager manager = new LinearLayoutManager(this);

        manager.setStackFromEnd(true);

        chatList.setLayoutManager(manager);
        chatList.setAdapter(adapter);

    }

    @OnClick(R.id.button_send)
    public void sendMessage(){
        Message message = new Message();

        message.setDate(DateHelper.getCurrentDate());
        message.setHour(DateHelper.getCurrentHour());
        message.setContent(etTypeMessage.getText().toString());
        message.setDeliver(User.formatEmail(FirebaseHelper.getInstance().getCurrentUserReference().getEmail()));

        /// REVIEW THIS !!!
        String chatPath = chat.getChatPath();

        String messageKey = DateHelper.replaceCharactersInDate(DateHelper.getExactCurrentDate(),"_"); //Message.createChatKey(FirebaseHelper.getInstance().getCurrentUserReference().getEmail());

        controller.sendMessage(message,chatPath,messageKey);
        etTypeMessage.setText("");

    }

    private void dismissView(){
        this.finish();
    }
}
