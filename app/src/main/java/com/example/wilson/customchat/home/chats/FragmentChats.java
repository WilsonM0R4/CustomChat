package com.example.wilson.customchat.home.chats;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wilson.customchat.R;
import com.example.wilson.customchat.commons.ShareDataHelper;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wmora on 7/15/16.
 */
public class FragmentChats extends Fragment implements ChatView{

    @Bind(R.id.chats_recycler) RecyclerView chatsRecycler;
    @Bind(R.id.noChatsText) TextView noChatsText;

    private static final String TAG = "ChatView";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View homeRootView =  inflater.inflate(R.layout.fragment_chats,container,false);

        if(homeRootView!=null){
            ButterKnife.bind(this,homeRootView);
        }

        ChatController controller = new ChatControllerImplementation();
        controller.setView(this);
        controller.onCreate();

        return homeRootView;
    }

    @Override
    public void newMessage() {

    }

    @Override
    public void deleteMessage() {

    }

    @Override
    public void showChats(final ArrayList<Chat> chats) {
        Log.d(TAG,"chats are "+chats);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this.getContext());
        ChatAdapter adapter = new ChatAdapter(chats);
        adapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = chatsRecycler.getChildAdapterPosition(v);

                ShareDataHelper.getInstance().setChat(chats.get(pos));

                Intent intent = new Intent(getActivity().getBaseContext(),ChatActivity.class);
                startActivity(intent);

            }
        });

        chatsRecycler.setLayoutManager(manager);
        chatsRecycler.setAdapter(adapter);
        chatsRecycler.setVisibility(View.VISIBLE);
        noChatsText.setVisibility(View.GONE);

    }

}
