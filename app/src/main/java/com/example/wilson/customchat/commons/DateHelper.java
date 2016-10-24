package com.example.wilson.customchat.commons;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by wmora on 10/24/16.
 */

public class DateHelper {

    public static String getCurrentDate(){
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        return format.format(Calendar.getInstance().getTime());
    }

    public static String getCurrentHour(){
        DateFormat format = new SimpleDateFormat("h:mm a");
        return format.format(Calendar.getInstance().getTime());
    }

}
