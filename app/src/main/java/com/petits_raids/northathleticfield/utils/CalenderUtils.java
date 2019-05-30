package com.petits_raids.northathleticfield.utils;

import java.util.Calendar;

public class CalenderUtils {

    private static Calendar calendar = Calendar.getInstance();

    public static int getTotalDays(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,1);
        calendar.setTimeInMillis(calendar.getTimeInMillis() - 24 * 60 * 60 * 1000);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getTodayDate(){
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getTodayMonth(){
        return calendar.get(Calendar.MONTH);
    }
}
