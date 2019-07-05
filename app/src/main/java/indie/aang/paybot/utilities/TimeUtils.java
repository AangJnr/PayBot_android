package indie.aang.paybot.utilities;

import android.content.Context;


import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by ordgen on 9/7/17.
 */

public class TimeUtils {
    private static final int SECOND = 1000;
    private static final int MINUTE = 60 * SECOND;
    private static final int HOUR = 60 * MINUTE;
    private static final int DAY = 24 * HOUR;



    public String convertTimeWithTimeZome(long time){
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.setTimeInMillis(time);
        return (cal.get(Calendar.YEAR) + " " + (cal.get(Calendar.MONTH) + 1) + " "
                + cal.get(Calendar.DAY_OF_MONTH) + " " + cal.get(Calendar.HOUR_OF_DAY) + ":"
                + cal.get(Calendar.MINUTE));
    }

    public static String getCurrentDateTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return df.format(c.getTime());
    }


    public static String getDateTimestamp() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("EEEE, MMM dd yyyy HH:mm", Locale.getDefault());
        return df.format(c.getTime());
    }

    public static String getDateTimestampForReceipt() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("EEE, MMM-dd yyyy HH:mm", Locale.getDefault());
        return df.format(c.getTime());
    }


    public static String formatDateAndTime(Calendar calendar) {
        SimpleDateFormat df = new SimpleDateFormat("EEEE dd MMM, yyyy", Locale.getDefault());
        return df.format(calendar.getTime());
    }

    public static String getTodayDateTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return df.format(c.getTime());
    }



    public static String getDateTime(int months) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, months);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return df.format(c.getTime());
    }



    public static String convertToPrettyTime(long timeInMillis){
      PrettyTime prettyTime = new PrettyTime();

        return prettyTime.format(new Date(timeInMillis));
    }


    public static String convertToPrettyTime(String date) throws ParseException {
        PrettyTime prettyTime = new PrettyTime();
        Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(date);
        return prettyTime.format(date1);
    }



    public static Calendar getCreatedDate(String s){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date;
        try {
            date = sdf.parse(s.substring(0, 10));
            cal.setTime(date);
        } catch (ParseException e) {e.printStackTrace();}
        return cal;
    }



}
