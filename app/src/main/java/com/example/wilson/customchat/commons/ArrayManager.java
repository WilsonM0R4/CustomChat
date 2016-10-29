package com.example.wilson.customchat.commons;

import com.example.wilson.customchat.home.chats.Message;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by wmora on 10/27/16.
 */

public class ArrayManager {

    public static final int ORDER_TYPE_ASCENDANT = 0;
    public static final int ORDER_TYPE_DESCENDANT = 1;

    public static ArrayList<Map<String,Map<String, String>>> orderMapArray(ArrayList<Map<String,Map<String, String>>> mapArray,  int orderType){

        ArrayList<Map<String,Map<String, String>>> temp = new ArrayList<>();
        ArrayList<Map<String,Map<String, String>>> forReturn = new ArrayList<>();
        temp.addAll(mapArray);

        int count = 0;
        for (Map<String,Map<String, String>> map : temp){

            ArrayList<String> tempKeys = new ArrayList<>();
            tempKeys.addAll(map.keySet());

            int firstKey = Integer.parseInt(tempKeys.get(count));
            int secondKey = Integer.parseInt(tempKeys.get(count+1));

            int pos = count;

            if(firstKey < secondKey){
                forReturn.add(pos,map);
                forReturn.add(pos+1,temp.get(count+1));
            }else{
                forReturn.add(pos,temp.get(count+1));
                forReturn.add(pos+1,map);
            }

        }

        return forReturn;
    }

    public static ArrayList<Message> orderMessageArrayArray(ArrayList<Message> mapArray, int orderType){

        ArrayList<Message> temp = new ArrayList<>();
        ArrayList<Message> forReturn = new ArrayList<>();
        temp.addAll(mapArray);

        int count = 0;
        for (Message message : temp){

            String date;

        }

        return forReturn;
    }

}
