package com.example.little_time.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    public static Boolean is_today(String inTime) {  //true为今天，false不是今天
        SimpleDateFormat date = new SimpleDateFormat("y-M-d");
        Date today = new Date();
        String fToday = date.format(today), fInTime = new String();
        boolean zero = true;
        for (int i = 0; i < inTime.length(); i++) {
            if (inTime.charAt(i) == ' ')
                break;
            if (zero)
                fInTime = fInTime + inTime.charAt(i);
            else {
                if (inTime.charAt(i) != '0') {
                    zero = true;
                    fInTime = fInTime + inTime.charAt(i);
                }
            }
            if (inTime.charAt(i) == '-')
                zero = false;
        }
        if (fToday == fInTime)
            return true;
        return false;
    }

    //今天在输入时间之前
    public static Boolean todayIsBefore(Date time) {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        String to = date.format(today), de = date.format(time);
        if (to.compareTo(de) < 0)
            return true;
        return false;
    }

    //今天是输入时间
    public static Boolean todayIsNow(Date time) {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        String to = date.format(today), de = date.format(time);
        if (to.compareTo(de) == 0)
            return true;
        return false;
    }

    //今天在输入时间之后
    public static Boolean todayIsAfter(Date time) {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        String to = date.format(today), de = date.format(time);
        if (to.compareTo(de) > 0)
            return true;
        return false;
    }
}
