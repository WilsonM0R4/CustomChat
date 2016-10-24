package com.example.wilson.customchat.home.chats;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wilson.customchat.R;
import com.example.wilson.customchat.commons.DateHelper;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by wmora on 10/19/16.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> implements View.OnClickListener{

    private View view;
    private ArrayList<Chat> dataSource;
    private ArrayList<Message> messages;

    private View.OnClickListener listener;

    public ChatAdapter(ArrayList<Chat> dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_list_item,null);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        messages = new ArrayList<>();
        messages.addAll(dataSource.get(position).getMessages());
        holder.bindView(messages.get(messages.size()-1));
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    @Override
    public void onClick(View v) {
        if(v!=null){
            listener.onClick(v);
        }
    }

    public void setOnItemClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private View itemView;
        private TextView txtContact,txtContent,txtDateHour;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

            txtContact = (TextView)itemView.findViewById(R.id.chat_contact_name);
            txtContent = (TextView)itemView.findViewById(R.id.chat_last_content);
            txtDateHour = (TextView)itemView.findViewById(R.id.chat_message_date);
        }

        public void bindView(Message message){

            String hour = message.getHour();
            String date = message.getDate();

            Log.d("bind","hour is "+hour);
            Log.d("bind","date is "+date);

            txtContact.setText(message.getDeliver());
            txtContent.setText(message.getContent());

            if(date.equals(DateHelper.getCurrentDate())){
                txtDateHour.setText(hour);
            }else{
                txtDateHour.setText(date);
            }


        }


    }
}
