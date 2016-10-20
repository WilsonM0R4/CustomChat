package com.example.wilson.customchat.home.chats;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wilson.customchat.R;

import java.util.Map;

/**
 * Created by wmora on 10/19/16.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private View view;
    private Map<String,Object> dataSource;

    public ChatAdapter(Map<String,Object> dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_list_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        private View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

        }

        public void bindView(Message message){

        }
    }
}
