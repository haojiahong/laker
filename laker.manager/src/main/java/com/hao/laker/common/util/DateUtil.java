package com.hao.laker.common.util;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


@Slf4j
public class DateUtil {

    public static String date2Ymd(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    public static String date2YmdPoint(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        return simpleDateFormat.format(date);
    }

    public static String date2YmdChinese(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy年MM月dd日");
        return simpleDateFormat.format(date);
    }

    public static String date2Md(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
        return simpleDateFormat.format(date);
    }

    public static String date2Hms(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static String date2SimpleHms(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.format(date);
    }

    public static String date2Hm(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return simpleDateFormat.format(date);
    }

    public static String formatDate(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static String date2HmChinese(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy年MM月dd日 HH:mm");
        return simpleDateFormat.format(date);
    }

    public static String date2SimpleHm(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return simpleDateFormat.format(date);
    }

    public static String date2SimpleYmd(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    public static Date string2Date(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date toToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date oneMinuteLater() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 1);
        return calendar.getTime();
    }

    public static boolean between(Date start, Date end) {
        if (start.after(end)) return false;
        Date today = new Date();
        return start.before(today) && end.after(today);
    }

    public static int daysOfTwo(Date fDate, Date oDate) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(fDate);
        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
        aCalendar.setTime(oDate);
        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
        return (day2 - day1);
    }

    public static Date strToDate(String dateStr, String formatStr) {
        DateFormat dd = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = dd.parse(dateStr);
        } catch (Exception e) {
            log.error("字符串转化时间出错:", e);
        }
        return date;
    }

    //    //获取今天凌晨的时间戳
    public static Integer getTodayBeginTimeStamp() {
        int y, m, d;
        Calendar cal = Calendar.getInstance();
        y = cal.get(Calendar.YEAR);
        m = cal.get(Calendar.MONTH);
        d = cal.get(Calendar.DATE);
        cal.set(y, m, d, 0, 0, 0);//时、分、秒，设置成0，获取凌晨的时间
        return (int) (cal.getTimeInMillis() / 1000);
    }

    //获取明天凌晨的时间戳
    public static Integer getTomorrowBeginTimeStamp() {
        int y, m, d;
        Calendar cal = Calendar.getInstance();
        y = cal.get(Calendar.YEAR);
        m = cal.get(Calendar.MONTH);
        d = cal.get(Calendar.DATE) + 1;
        cal.set(y, m, d, 0, 0, 0);//时、分、秒，设置成0，获取凌晨的时间
        return (int) (cal.getTimeInMillis() / 1000);
    }

    public static Integer getTomorrowBeginTimeStamp(Date date) {
        int y, m, d;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        y = cal.get(Calendar.YEAR);
        m = cal.get(Calendar.MONTH);
        d = cal.get(Calendar.DATE);
        cal.set(y, m, d, 0, 0, 0);//时、分、秒，设置成0，获取凌晨的时间
        return (int) (cal.getTimeInMillis() / 1000);
    }

    public static Date getTodayBeginDate() {
        int y, m, d;
        Calendar cal = Calendar.getInstance();
        y = cal.get(Calendar.YEAR);
        m = cal.get(Calendar.MONTH);
        d = cal.get(Calendar.DATE);
        cal.set(y, m, d, 0, 0, 0);//时、分、秒，设置成0，获取凌晨的时间
        return cal.getTime();
    }

    public static Date getTomorrowBeginDate() {
        int y, m, d;
        Calendar cal = Calendar.getInstance();
        y = cal.get(Calendar.YEAR);
        m = cal.get(Calendar.MONTH);
        d = cal.get(Calendar.DATE) + 1;
        cal.set(y, m, d, 0, 0, 0);//时、分、秒，设置成0，获取凌晨的时间
        return cal.getTime();
    }

    public static Integer getTimeStampByStr(String time) {
        return getTimeStampByStr(time, "yyyy-MM-dd HH:mm:ss");
    }

    public static Integer getTimeStamp(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return (int) (c.getTimeInMillis() / 1000);
    }

    public static Integer getTimeStampByStr(String time, String format) {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(new SimpleDateFormat(format).parse(time));
        } catch (Exception e) {
            log.error("转化日期出错", e);
        }
        return (int) (c.getTimeInMillis() / 1000);
    }

    public static Integer now() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    // 获取本周开始时间，以周日为第一天
    public static String getDateWeekStartTime() {
        Calendar currentDate = new GregorianCalendar();
        currentDate.setFirstDayOfWeek(Calendar.SUNDAY);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return DateUtil.date2Ymd(currentDate.getTime());
    }

    // 获得本月第一天0点时间
    public static String getTimesMonthStartTime() {
        Calendar currentDate = new GregorianCalendar();
        currentDate.setFirstDayOfWeek(Calendar.SUNDAY);
        currentDate.set(Calendar.DATE, 1);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        return DateUtil.date2Ymd(currentDate.getTime());
    }

    public static Integer getNextMonthTimeStamp() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, 1);
        return (int) (cal.getTimeInMillis() / 1000);
    }

    /**
     * 日期转时间戳
     *
     * @param date
     * @return
     */
    public static Integer date2TimeStamp(Date date) {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(date);
        } catch (Exception e) {
            log.error("转化日期出错", e);
        }
        return (int) (c.getTimeInMillis() / 1000);
    }

    //判断是否为上半年， true 上半年（7月1号0点前）， false：下半年
    public static boolean isFirstHalfYear() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.MONTH, 6);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date limitTime = cal.getTime();
        return limitTime.compareTo(new Date()) > 0;
    }

}
