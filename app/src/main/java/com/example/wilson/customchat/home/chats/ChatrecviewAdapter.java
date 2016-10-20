package com.example.wilson.customchat.home.chats;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wilson.customchat.R;
import com.example.wilson.customchat.User;
import com.example.wilson.customchat.domain.FirebaseHelper;

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
        Message message = new Message();
        boolean sender;
        String currentUser = User.formatEmail(FirebaseHelper.getInstance().getCurrentUserReference().getEmail());
        String deliver = User.formatEmail(dataSource.get(Chat.SENDER_PATH).toString());

        message.setContent(dataSource.get(Chat.CONTENT_PATH).toString());
        message.setDate(dataSource.get(Chat.DATE_PATH).toString());
        message.setHour(dataSource.get(Chat.HOUR_PATH).toString());
        message.setDeliver(deliver);

        sender = currentUser.equals(deliver);

        holder.bindView(sender,message);
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private View view ;
        private TextView txtContent, txtHour;


        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            txtContent = (TextView)view.findViewById(R.id.chat_message);
            txtHour = (TextView)view.findViewById(R.id.chat_hour);
        }

        @TargetApi(Build.VERSION_CODES.M)
        public void bindView(boolean sender, Message message){

            //appearance for item
            if(sender){
                view.setForegroundGravity(View.TEXT_ALIGNMENT_VIEW_END);
                view.setBackgroundColor(Chat.COLOR_SENDER);
            }else{
                view.setForegroundGravity(View.TEXT_ALIGNMENT_VIEW_START);
                view.setBackgroundColor(Chat.COLOR_RECEIVER);
            }

            //data for item
            txtContent.setText(message.getContent());
            txtHour.setText(message.getHour());

        }

    }

}
