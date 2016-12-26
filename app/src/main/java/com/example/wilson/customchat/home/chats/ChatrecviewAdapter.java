package com.example.wilson.customchat.home.chats;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wilson.customchat.R;
import com.example.wilson.customchat.User;
import com.example.wilson.customchat.commons.DateHelper;
import com.example.wilson.customchat.domain.FirebaseHelper;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by wmora on 10/19/16.
 */

public class ChatRecViewAdapter extends RecyclerView.Adapter<ChatRecViewAdapter.ViewHolder> {


    private View item;
    private Map<String, Map<String, String>> dataSource;
    private ArrayList<String> keys = new ArrayList<>();

    /***
     *  Constructor
     * **/

    public ChatRecViewAdapter(Map<String, Map<String, String>> dataSource){
        this.dataSource = dataSource;
        this.keys.addAll(dataSource.keySet());
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
        Map<String, String> message = dataSource.get(keys.get(position));
        boolean sender = User.formatEmail(message.get(Chat.SENDER_PATH))
                .equals(User.formatEmail(FirebaseHelper.getInstance().getCurrentUserReference().getEmail()));

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
        private RelativeLayout container;


        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;

            container = (RelativeLayout)view.findViewById(R.id.messages_container);

            layoutReceiver = (LinearLayout) view.findViewById(R.id.receiver_bubble);
            layoutSender = (LinearLayout) view.findViewById(R.id.sender_bubble);

            txtContent = (TextView)view.findViewById(R.id.chat_message_receiver);
            txtHour = (TextView)view.findViewById(R.id.chat_hour_receiver);

            txtContentSender = (TextView)view.findViewById(R.id.chat_message_sender);
            txtHourSender = (TextView)view.findViewById(R.id.chat_hour_sender);
        }

        @TargetApi(Build.VERSION_CODES.M)
        public void bindView(boolean sender, Map<String,String> message){

            //appearance for item
            if(sender){
                layoutSender.setVisibility(View.VISIBLE);
                layoutReceiver.setVisibility(View.GONE);

                txtContentSender.setText(message.get(Chat.CONTENT_PATH));

                String date = message.get(Chat.DATE_PATH);
                if(!date.equals(DateHelper.getCurrentDate())){
                    txtHourSender.setText(date);
                    Log.d("Adapter","message is not from today, is from "+date);
                }else{
                    Log.d("Adapter","message is form today, at "+message.get(Chat.HOUR_PATH));
                    txtHourSender.setText(message.get(Chat.HOUR_PATH));
                }

            }else{
                layoutSender.setVisibility(View.GONE);
                layoutReceiver.setVisibility(View.VISIBLE);

                txtContent.setText(message.get(Chat.CONTENT_PATH));

                String date = message.get(Chat.DATE_PATH);
                if(!date.equals(DateHelper.getCurrentDate())){
                    Log.d("Adapter","message is not from today, is from "+date);
                    txtHour.setText(date);
                }else{
                    Log.d("Adapter","message is form today, at "+message.get(Chat.HOUR_PATH));
                    txtHour.setText(message.get(Chat.HOUR_PATH));
                }

            }

            //data for item


        }

    }

}
