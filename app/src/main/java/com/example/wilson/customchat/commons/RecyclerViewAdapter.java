package com.example.wilson.customchat.commons;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wilson.customchat.R;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wilson on 17/07/2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    ArrayList<HashMap<String, String>> dataMap;
    ArrayList<String> keys;

    public RecyclerViewAdapter(ArrayList<HashMap<String, String>> dataMap,ArrayList<String> keys){
        this.dataMap = dataMap;
        this.keys = keys;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View content = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item,parent,false);
        ButterKnife.bind(this,content);
        ViewHolder holder = new ViewHolder(content);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String imagePath = dataMap.get(position).get(keys.get(0));
        String title = dataMap.get(position).get(keys.get(1));
        String subtitle = dataMap.get(position).get(keys.get(2));
        holder.bind(imagePath,title,subtitle);

    }

    @Override
    public int getItemCount() {
        return dataMap.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.itemImage) ImageView itemImage;
        @Bind(R.id.textItemTitle) TextView textItemTitle;
        @Bind(R.id.textItemSubtitle) TextView textItemSubtitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(String imagePath,String title, String subtitle){
            textItemTitle.setText(title);
            textItemSubtitle.setText(subtitle);
        }


    }
}
