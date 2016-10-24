package com.example.wilson.customchat.home.chats;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wilson.customchat.R;
import com.example.wilson.customchat.User;
import com.example.wilson.customchat.domain.FirebaseHelper;

import java.util.ArrayList;

/**
 * Created by wmora on 10/19/16.
 */

public class ChatRecViewAdapter extends RecyclerView.Adapter<ChatRecViewAdapter.ViewHolder> {


    private View item;
    private ArrayList<Message> dataSource;

    /***
     *  Constructor
     * **/

    public ChatRecViewAdapter(ArrayList<Message> dataSource){
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
        Message message = dataSource.get(position);
        boolean sender = message.getDeliver().equals(User.formatEmail(FirebaseHelper.getInstance().getCurrentUserReference().getEmail()));

        holder.bindView(sender,message);
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private View view ;
        private TextView txtContent, txtHour,txtContentSender,txtHourSender;
        private LinearLayout layoutSender,layoutReceiver;


        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;

            layoutReceiver = (LinearLayout) view.findViewById(R.id.receiver_bubble);
            layoutSender = (LinearLayout) view.findViewById(R.id.sender_bubble);

            txtContent = (TextView)view.findViewById(R.id.chat_message_receiver);
            txtHour = (TextView)view.findViewById(R.id.chat_hour_receiver);

            txtContentSender = (TextView)view.findViewById(R.id.chat_message_sender);
            txtHourSender = (TextView)view.findViewById(R.id.chat_hour_sender);
        }

        @TargetApi(Build.VERSION_CODES.M)
        public void bindView(boolean sender, Message message){

            //appearance for item
            if(sender){
                layoutSender.setVisibility(View.VISIBLE);
                layoutReceiver.setVisibility(View.GONE);

                txtContentSender.setText(message.getContent());
                txtHourSender.setText(message.getHour());
            }else{
                layoutSender.setVisibility(View.GONE);
                layoutReceiver.setVisibility(View.VISIBLE);

                txtContent.setText(message.getContent());
                txtHour.setText(message.getHour());
            }

            //data for item


        }

    }

}
