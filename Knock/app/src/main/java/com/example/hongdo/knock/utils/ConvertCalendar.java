package com.example.hongdo.knock.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Win 8.1 Version 2 on 24/10/2017.
 */

public class ConvertCalendar {
    public static String convertCtoSDate(Calendar calendar) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM", Locale.getDefault());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String s = sdf.format(calendar.getTime());
        return s;
    }
    public static String convertCtoSTime(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String s = sdf.format(calendar.getTime());
        return s;
    }
}
