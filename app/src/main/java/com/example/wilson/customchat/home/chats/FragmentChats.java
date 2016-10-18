package com.example.wilson.customchat.home.chats;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wilson.customchat.R;

import butterknife.ButterKnife;

/**
 * Created by wmora on 7/15/16.
 */
public class FragmentChats extends Fragment implements ChatView{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View homeRootView =  inflater.inflate(R.layout.fragment_chats,container,false);

        if(homeRootView!=null){
            ButterKnife.bind(this,homeRootView);
        }

        ChatController controller = new ChatControllerImplementation();
        controller.onCreate();

        return homeRootView;
    }

    @Override
    public void newMessage() {

    }

    @Override
    public void deleteMessage() {

    }
}
