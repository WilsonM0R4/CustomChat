package com.example.wilson.customchat.commons;

import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by wmora on 9/26/16.
 */

public class AsyncLoad extends AsyncTask<String,Void,ArrayList<String>> {

    @Override
    public void onPreExecute(){

    }

    @Override
    protected ArrayList<String> doInBackground(String... params) {
        return null;
    }

    @Override
    public void onPostExecute(ArrayList<String> resultData){

    }
}
