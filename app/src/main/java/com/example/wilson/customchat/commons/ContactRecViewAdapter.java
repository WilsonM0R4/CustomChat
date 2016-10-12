package com.example.wilson.customchat.commons;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wilson.customchat.R;
import com.example.wilson.customchat.home.contacts.Contact;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wilson on 17/07/2016.
 */
public class ContactRecViewAdapter extends RecyclerView.Adapter<ContactRecViewAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener{

    private static final String TAG = "RecyclerView";

    private ArrayList<Contact> dataMap;
    private View content;
    private View parentView;
    private ContextMenu contextMenu;
    private View.OnClickListener onClickListener;
    private View.OnLongClickListener onLongClickListener;

    public ContactRecViewAdapter(ArrayList<Contact> dataMap){
        this.dataMap = dataMap;
        Log.d(TAG,"Rec created");
        Log.d(TAG,"dataMap is: "+dataMap);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        content = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item,parent,false);
        ButterKnife.bind(this,content);
        Log.d(TAG,"view created");
        content.setOnClickListener(this);
        content.setOnLongClickListener(this);
        return new ViewHolder(content);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String imagePath = dataMap.get(position).getContactProfileImagePath();
        String title = dataMap.get(position).getContactUsername();
        String subtitle = dataMap.get(position).getContactState();
        Log.d(TAG,"received data is: "+imagePath+", "+title+", "+subtitle);
        holder.bind(imagePath,title,subtitle);
    }


    @Override
    public int getItemCount() {
        return dataMap.size();
    }

    /**
     * onClick listener
     * **/

    @Override
    public void onClick(View v) {
        if(v!=null){
            onClickListener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    @Override
    public boolean onLongClick(View v) {
        if(v!=null){
            onLongClickListener.onLongClick(v);
        }
        return true;
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener){
        this.onLongClickListener = onLongClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        /*@Bind(R.id.itemImage)*/ ImageView itemImage;
        /*@Bind(R.id.textItemTitle)*/ TextView textItemTitle;
        /*@Bind(R.id.textItemSubtitle)*/ TextView textItemSubtitle;

        public ViewHolder(View itemView) {
            super(itemView);
            itemImage = (ImageView)itemView.findViewById(R.id.itemImage);
            textItemTitle = (TextView)itemView.findViewById(R.id.textItemTitle);
            textItemSubtitle = (TextView)itemView.findViewById(R.id.textItemSubtitle);
        }

        public void bind(String imagePath,String title, String subtitle){
            textItemTitle.setText(title);
            textItemSubtitle.setText(subtitle);
        }


    }
}
