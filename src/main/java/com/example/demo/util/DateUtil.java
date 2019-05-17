package com.example.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public final class DateUtil {
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    //public static final String YYMMDDHHmmss 		= "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_HHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDDHHmmss = "yyyyMMddHHmmss";
    public static final String HHMMSS = "HHmmss";
    public static final String HH_MM_SS = "HH:mm:ss";
    public static final String HHMM = "HHmm";
    public static final String HH_MM = "HH:mm";
    public static final String HH_MM_SS_START = " 00:00:00";
    public static final String HH_MM_SS_END = " 23:59:59";

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * hhmmss
     *
     * @param date
     * @return
     * @date 2015-3-18 下午2:35:43
     */
    public static final int getHHMMSSInt(Date date) {
        String hhmmss = format(date, HHMMSS);

        return Integer.parseInt(hhmmss);
    }

    /**
     * yyyymmdd
     *
     * @param date
     * @return
     */
    public static final int getYYYYMMDDInt(Date date) {
        String yyyymmdd = format(date, YYYYMMDD);
        return Integer.parseInt(yyyymmdd);
    }

    /**
     * 获取当天
     *
     * @return
     */
    public static final Date getToday() {
        String yyyymmdd = format(new Date(), YYYYMMDD);
        return parse(yyyymmdd, YYYYMMDD);
    }

    /**
     * 判断是不是今天
     *
     * @param date
     * @return boolean
     */
    public static final boolean isOnToday(Date date) {
        String yyyymmdd = format(date, YYYYMMDD);
        String today = format(new Date(), YYYYMMDD);
        return yyyymmdd.equals(today);
    }

    /**
     * hhmm
     *
     * @param date
     * @return
     */
    public static final int getHHMMInt(Date date) {
        String hhmm = format(date, HHMM);

        return Integer.parseInt(hhmm);
    }

    /**
     * 时间拼接
     *
     * @param yyyymmdd
     * @param hhmmss
     * @return
     * @date 2015-3-18 下午2:59:11
     */
    public static final String concatTime(String yyyymmdd, int hhmmss) {
        StringBuilder sb = new StringBuilder();
        sb.append(yyyymmdd);
        String str = "" + hhmmss;
        for (int i = 0; i < 6 - str.length(); i++) {
            sb.append("0");
        }
        sb.append(str);
        return sb.toString();

    }

    /**
     * 时间转换
     *
     * @param date
     * @return String: "yyyy-MM-dd HH:mm:ss"
     */
    public static final String format(final Date date) {
        return format(date, YYYY_MM_DD_HHmmss);
    }

    /**
     * 时间转换
     *
     * @param date:毫秒
     * @return String
     */
    public static final String format(long date) {
        try {
            Date datetime = new Date(date);
            return format(datetime);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 时间转换
     *
     * @param date
     * @param pattern
     * @return String
     */
    public static final String format(final Date date, final String pattern) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            return format.format(date);
        } catch (Exception e) {

        }

        return null;

    }

    /**
     * @param text
     * @param pattern
     * @return Date
     */
    public static final Date parse(final String text, final String pattern) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            return format.parse(text);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 获取昨天
     *
     * @return
     */
    public static final Date getYesterday(final Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 获取明天
     *
     * @return
     */
    public static final Date getTomorrow(final Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * 时长转换成时分秒
     *
     * @param duration:秒数
     * @return
     */
    public static final String formatDuration(long duration) {
        if (duration <= 0) {
            return "--";
        }
        String pattern = "";
        long hours = duration / 3600;
        if (hours > 0) {
            pattern += hours + "时";
        }
        long minutes = (duration - hours * 3600) / 60;
        if (minutes > 0) {
            pattern += minutes + "分";
        }
        long seconds = duration - hours * 3600 - minutes * 60;
        if (seconds > 0) {
            pattern += seconds + "秒";
        }
        return pattern;
    }

    /**
     * 时长转换成时分秒
     * @param duration:秒数
     * @return
     */
    public static final String formatDurationNull(long duration){
        if(duration <= 0){return "";}
        String pattern = "";
        long hours = duration/3600;
        if(hours > 0){
            pattern += hours + ":";
        } else {
            pattern += "0" + ":";
        }
        long minutes = (duration - hours * 3600)/60;
        if(minutes > 0){
            pattern += minutes + ":";
        } else {
            pattern += "00" + ":";
        }
        long seconds = duration - hours * 3600 - minutes * 60;
        if(seconds > 0){
            pattern += seconds + "";
        }
        return pattern;
    }

    /**
     *
     * UTC(世界标准时间)
     * CST(北京时间)
     *  GMT(格林尼治平时) 精确度的问题目前不在使用
     * @param UTCString
     * @return
     */
    public static String UTCStringtODefaultString(String UTCString) {
        try {
            UTCString = UTCString.replace("Z", " UTC");
            SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = utcFormat.parse(UTCString);
            defaultFormat.setTimeZone(TimeZone.getTimeZone("UTC+08:00"));
            return defaultFormat.format(date);
        } catch (ParseException pe) {
            pe.printStackTrace();
            return null;
        }
    }

    public static List<String> queryData(Date startAt, Date endAt) {
        List<String> dates = new ArrayList<>();
        Calendar start = Calendar.getInstance();
        start.setTime(startAt);
        Calendar end = Calendar.getInstance();
        end.setTime(endAt);
        while (start.before(end) || start.equals(end)) {
            dates.add(dateFormat.format(start.getTime()));
            start.add(Calendar.DAY_OF_YEAR, 1);
        }
        return dates;
    }

    public static List<String> queryData(String startAt, String endAt) {
        List<String> dates = new ArrayList<>();
        try {
            Date startDate = dateFormat.parse(startAt);
            Date endDate = dateFormat.parse(endAt);
            dates.addAll(queryData(startDate, endDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dates;
    }

    public static void main(String[] args) {
        String str = "2018-11-28T06:01:15.369Z";
        String strs =UTCStringtODefaultString(str);
        System.out.println(strs);
        List<String> list = queryData("2018-12-03 12:00:00", "2018-12-11");
        list.stream().forEach(s -> {
            System.out.println(s);
        });
        System.out.println(list);
    }


}
