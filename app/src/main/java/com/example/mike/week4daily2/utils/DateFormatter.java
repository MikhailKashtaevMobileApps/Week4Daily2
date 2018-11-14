package com.example.mike.week4daily2.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

    public static String formatDate(String shittyFormatDate){
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(shittyFormatDate);
            return new SimpleDateFormat("MM/dd/yyyy, HH:mm").format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return shittyFormatDate;
        }
    }

}
