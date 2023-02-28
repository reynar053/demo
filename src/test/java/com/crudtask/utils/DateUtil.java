package com.crudtask.utils;

import java.util.Calendar;
import java.util.Date;

public abstract class DateUtil {
    private static final int HALF_OF_SECOND = 500;

//    public static boolean compareDates(Date date, Date date2) {
//        return roundDate(date).compareTo(roundDate(date2)) == 0;
//    }

    public static Calendar msToCalendar(long ms){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ms);
        return calendar;
    }

    private static Date roundCalendarDate(Calendar date) {
        date.add(Calendar.MILLISECOND, HALF_OF_SECOND);
        date.set(Calendar.MILLISECOND, 0);
        return date.getTime();
    }

    public static boolean compareCalendars(Calendar date, Calendar date2) {
        return roundCalendarDate(date).compareTo(roundCalendarDate(date2)) == 0;
    }
}
