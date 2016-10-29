package com.example.wilson.customchat.commons;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by wmora on 10/24/16.
 */

public class DateHelper {

    public static String getCurrentDate(){
        DateFormat format = new SimpleDateFormat("yy/MM/dd");
        return format.format(Calendar.getInstance().getTime());
    }

    public static String getCurrentHour(){
        DateFormat format = new SimpleDateFormat("h:mm a");
        return format.format(Calendar.getInstance().getTime());
    }

    public static String getExactCurrentDate(){
        DateFormat format = new SimpleDateFormat("MMddyy_hh_mm_ss_mmss");
        return format.format(Calendar.getInstance().getTime());
    }

    public static String replaceCharactersInDate(String date, String character){
        if(date.contains(character)){
            date = date.replace(character,"");
        }
        return date;
    }

}
