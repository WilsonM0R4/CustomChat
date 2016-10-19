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

public class ChatRecViewAdapter extends RecyclerView.Adapter<ChatRecViewAdapter.ViewHolder> {


    private View item;
    private Map<String,Object> dataSource;

    /***
     *  Constructor
     * **/

    public ChatRecViewAdapter(Map<String,Object> dataSource){
        this.dataSource = dataSource;
    }

    /***
     * Override methods
     * **/
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        item = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item,null);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.bindView();
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private View view ;
        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
        }

        public void bindView(boolean sender){
            if(sender){

            }
        }

    }

}
