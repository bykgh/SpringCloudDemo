package com.byk.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Title: 日期 工具类
 * Description:
 * Copyright: Copyright (c)2011
 * Company: pay
 *
 * @author huakui.zhang
 */
public class DateUtil {

    static GregorianCalendar calendar = new GregorianCalendar();

    public static String formatNow(String format) {
        return formatDate(new Date(), format);
    }

    public static String today() {
        return formatDate(new Date(), "yyyy-MM-dd");
    }

    public static boolean isInBetweenTimes(String startTime, String endTime) {
        Date nowTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(nowTime);
        if (time.compareTo(startTime) >= 0 && time.compareTo(endTime) <= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断日期之间的间隔
     *
     * @param
     * @return boolean
     */
    public static boolean compareDays(Date beginTime, Date endTime, int days) {
        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);
        begin.set(Calendar.HOUR_OF_DAY, 0);
        begin.set(Calendar.MINUTE, 0);
        begin.set(Calendar.SECOND, 0);
        begin.set(Calendar.MILLISECOND, 0);
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);
        if ((end.getTime().getTime() - begin.getTime().getTime()) / (1000 * 60 * 60 * 24) <= days) {
            return true;
        } else {
            return false;
        }
    }

    public static String getNextYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        return formatDate(calendar.getTime(), "yyyy-MM-dd");
    }

    /**
     * 根据偏移天数，获取新的Date对象
     *
     * @param date
     * @param days 正数表示增加的天数，负数表示减少的天数
     * @return
     */
    public static Date getDate(Date date, int days) {
        Day day = new Day(date);
        day.addDays(days);
        return day.getDate();
    }

    /**
     * 取得通用日期时间格式字符串
     *
     * @param Date
     * @return String
     */

    public static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    /**
     * 取得指定日期格式的 字符串
     *
     * @param Date
     * @return String
     */

    public static String formatDate(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static Date parseToTodayDesignatedDate(Date date, String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd " + time);
        Date end = null;
        try {
            end = sdf.parse(sdf1.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return end;
    }

    /**
     * 取得指定日期的明天
     *
     * @param date
     * @return
     */
    public static Date getNextDate(Date date) {
        return getDate(date, 1);
    }

    /**
     * 取得指定日期的昨天
     *
     * @param date
     * @return
     */
    public static Date getLastDate(Date date) {
        return getDate(date, -1);
    }

    /**
     * 比较 日期dt1 + 天数 days 与 dt2的差 如果大于： 返回 1 如果等于： 返回 0 如果小于： 返回 -1
     *
     * @param dt1  --
     *             日期1(忽略 时分秒）
     * @param days --
     *             日期1需要加上的天数
     * @param dt2  --
     *             日期1(忽略 时分秒）
     */
    public static int compare(Date dt1, int days, Date dt2) {
        // return getIntDate(getDate(dt1, days)) - getIntDate(dt2);
        int betweenDays = new Day(getDate(dt1, days)).daysBetween(new Day(dt2));
        return betweenDays;
        // if (dt1.after(dt2))
        // return betweenDays;
        // else
        // return -betweenDays;
    }


    /**
     * 检查curDate是否在startDate和endDate内
     *
     * @param curDate
     * @param startDate
     * @param endDate
     * @return
     */
    public static boolean isInDateRange(Date curDate, Date startDate,
                                        Date endDate) {
        if (startDate == null || curDate == null) {
            return false;
        }

        if (curDate.compareTo(startDate) >= 0) {
            if (endDate == null) {
                return true;
            } else if (curDate.compareTo(endDate) <= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 取得昨天Date对象
     *
     * @param 无
     * @return Date
     */
    public static Date getYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 字符串日期格式按照日期模式转换成日期
     *
     * @param sDate   --
     *                字符串的日期
     * @param pattern --
     *                日期格式 （比如：yyyy-MM-dd）
     * @return
     * @throws ParseException
     */
    public static Date parseToDate(String sDate, String pattern)
            throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        return sf.parse(sDate);
    }

    public static Date parseToDateWithyyyyMMdd(String sDate)
            throws ParseException {
        return parseToDate(sDate, "yyyy-MM-dd");
    }

    public static Date addMinuteToDate(Date date, int minutes) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.MINUTE, minutes);
        return ca.getTime();
    }

    public static Date addDayToDate(Date date, int days) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.DAY_OF_MONTH, days);
        return ca.getTime();
    }

    public static Date getMinTime(Date dt) {
        Date dt1 = null;
        try {
            dt1 = DateUtil.parseToDate(DateUtil.formatDate(dt, "yyyyMMdd"),
                    "yyyyMMdd");
        } catch (ParseException e) {
            System.out.println("date formate error ：" + dt + ".   "
                    + e.getMessage());
        }
        return dt1;
    }

    public static Date getMaxTime(Date dt) {
        Date dt1 = null;
        Calendar ca = Calendar.getInstance();
        ca.setTime(dt);
        ca.add(Calendar.DAY_OF_MONTH, 1);
        dt1 = ca.getTime();
        dt1 = DateUtil.getMinTime(dt1);
        ca.setTime(dt1);
        ca.add(Calendar.SECOND, -1);
        dt1 = ca.getTime();
        return dt1;
    }

    public static int getSecondsBetweenDate(Date before, Date after) {
        Long sec = after.getTime() - before.getTime();
        Long t = sec / 1000;
        if (t > 0) {
            return t.intValue();
        } else {
            return 0;
        }
    }

    public static int getMinutesBetweenDate(Date before, Date after) {
        Long sec = after.getTime() - before.getTime();
        Long t = sec / (1000 * 60);
        if (t > 0) {
            return t.intValue();
        } else {
            return 0;
        }
    }

}

