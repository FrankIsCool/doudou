package com.common.date;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
/*
 * 由于为了以后使用方便,所有方法的返回类型都设为了 java.util.Date 请在使用时根据自己的需要进行日期格式化处理,如:
 *
 * import java.text.SimpleDateFormat;SimpleDateFormat simpleDateFormat = new
 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss");String todayBegin =
 * simpleDateFormat.format
 * (DateUtils.getDayBegin());System.out.println(todayBegin );//输出结果为2017-10-26
 * 00:00:00
 */
/**
 * 日期工具类
 */
public class DatesUtil {
    /**
     * 获取当天的开始时间
     * @return
     */
    public static java.util.Date getDayBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取当天的结束时间
     * @return
     */
    public static java.util.Date getDayEnd() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 获取昨天的开始时间
     * @return
     */
    public static Date getBeginDayOfYesterday() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    /**
     * 获取昨天的结束时间
     * @return
     */
    public static Date getEndDayOfYesterDay() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    /**
     * 获取明天的开始时间
     * @return
     */
    public static Date getBeginDayOfTomorrow() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 获取明天的结束时间
     * @return
     */
    public static Date getEndDayOfTomorrow() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 获取本周的开始时间
     * @return
     */
    @SuppressWarnings("unused")
    public static Date getBeginDayOfWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getDayStartTime(cal.getTime());
    }

    /**
     * 获取本周的结束时间
     * @return
     */
    public static Date getEndDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }
    /**
     * 获取上上周的开始时间
     * @return
     */
    @SuppressWarnings("unused")
    public static Date getBeginDayOfLastTwoWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 14;
        }
        cal.add(Calendar.DATE, 2 - dayofweek -14);
        return getDayStartTime(cal.getTime());
    }
    /**
     * 获取上上周的结束时间
     * @return
     */
    public static Date getEndDayOfLastTwoWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfLastTwoWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }
    /**
     * 获取上周的开始时间
     * @return
     */
    @SuppressWarnings("unused")
    public static Date getBeginDayOfLastWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek - 7);
        return getDayStartTime(cal.getTime());
    }

    /**
     * 获取上周的结束时间
     * @return
     */
    public static Date getEndDayOfLastWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfLastWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }

    /**
     * 获取本月的开始时间
     * @return
     */
    public static Date getBeginDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        return getDayStartTime(calendar.getTime());
    }

    /**
     * 获取本月的结束时间
     * @return
     */
    public static Date getEndDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth() - 1, day);
        return getDayEndTime(calendar.getTime());
    }

    /**
     * 获取上月的开始时间
     * @return
     */
    public static Date getBeginDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 2, 1);
        return getDayStartTime(calendar.getTime());
    }

    /**
     * 获取上月的结束时间
     * @return
     */
    public static Date getEndDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 2, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth() - 2, day);
        return getDayEndTime(calendar.getTime());
    }

    public static void main(String[] args) {
        System.out.println(getBeginDayOfLastQuarter());
        System.out.println(getEndDayOfLastQuarter());
    }
    /**
     * 获取上个季度的开始时间
     * @return
     */
    public static Date getBeginDayOfLastQuarter() {
        int nowMonth = getNowMonth();
        Calendar calendar = Calendar.getInstance();
        if(1<=nowMonth&&nowMonth<=3){
            calendar.set(getNowYear()-1, 10, 1);
        }
        if(4<=nowMonth&&nowMonth<=6){
            calendar.set(getNowYear(), 0, 1);
        }
        if(7<=nowMonth&&nowMonth<=9){
            calendar.set(getNowYear(), 3, 1);
        }
        if(10<=nowMonth&&nowMonth<=12){
            calendar.set(getNowYear(), 6, 1);
        }
        return getDayStartTime(calendar.getTime());
    }
    /**
     * 获取上个季度的开始时间
     * @return
     */
    public static Date getEndDayOfLastQuarter() {
        int nowMonth = getNowMonth();
        Calendar calendar = Calendar.getInstance();
        if(1<=nowMonth&&nowMonth<=3){
            calendar.set(getNowYear()-1, 11, 1);
            int day = calendar.getActualMaximum(5);
            calendar.set(getNowYear(), 11, day);
        }
        if(4<=nowMonth&&nowMonth<=6){
            calendar.set(getNowYear(), 2, 1);
            int day = calendar.getActualMaximum(5);
            calendar.set(getNowYear(), 2, day);
        }
        if(7<=nowMonth&&nowMonth<=9){
            calendar.set(getNowYear(), 5, 1);
            int day = calendar.getActualMaximum(5);
            calendar.set(getNowYear(), 5, day);
        }
        if(10<=nowMonth&&nowMonth<=12){
            calendar.set(getNowYear(), 8, 1);
            int day = calendar.getActualMaximum(5);
            calendar.set(getNowYear(), 8, day);
        }
        return getDayEndTime(calendar.getTime());
    }
    /**
     * 获取上上个季度的开始时间
     * @return
     */
    public static Date getBeginDayOfLastTwoQuarter() {
        int nowMonth = getNowMonth();
        Calendar calendar = Calendar.getInstance();
        if(1<=nowMonth&&nowMonth<=3){
            calendar.set(getNowYear()-1, 7, 1);
        }
        if(4<=nowMonth&&nowMonth<=6){
            calendar.set(getNowYear(), 10, 1);
        }
        if(7<=nowMonth&&nowMonth<=9){
            calendar.set(getNowYear(), 0, 1);
        }
        if(10<=nowMonth&&nowMonth<=12){
            calendar.set(getNowYear(), 3, 1);
        }
        return getDayStartTime(calendar.getTime());
    }
    /**
     * 获取上上个季度的开始时间
     * @return
     */
    public static Date getEndDayOfLastTwoQuarter() {
        int nowMonth = getNowMonth();
        Calendar calendar = Calendar.getInstance();
        if(1<=nowMonth&&nowMonth<=3){
            calendar.set(getNowYear()-1, 8, 1);
            int day = calendar.getActualMaximum(5);
            calendar.set(getNowYear(), 8, day);
        }
        if(4<=nowMonth&&nowMonth<=6){
            calendar.set(getNowYear(), 11, 1);
            int day = calendar.getActualMaximum(5);
            calendar.set(getNowYear(), 11, day);
        }
        if(7<=nowMonth&&nowMonth<=9){
            calendar.set(getNowYear(), 2, 1);
            int day = calendar.getActualMaximum(5);
            calendar.set(getNowYear(), 2, day);
        }
        if(10<=nowMonth&&nowMonth<=12){
            calendar.set(getNowYear(), 5, 1);
            int day = calendar.getActualMaximum(5);
            calendar.set(getNowYear(), 5, day);
        }
        return getDayEndTime(calendar.getTime());
    }
    /**
     * 获取上上月的开始时间
     * @return
     */
    public static Date getBeginDayOfLastTwoMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 3, 1);
        return getDayStartTime(calendar.getTime());
    }

    /**
     * 获取上上月的结束时间
     * @return
     */
    public static Date getEndDayOfLastTwoMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 3, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth() - 3, day);
        return getDayEndTime(calendar.getTime());
    }
    /**
     * 获取本年的开始时间
     * @return
     */
    public static Date getBeginDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear());
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);
        return getDayStartTime(cal.getTime());
    }
    /**
     * 获取本年的结束时间
     * @return
     */
    public static java.util.Date getEndDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear());
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DATE, 31);
        return getDayEndTime(cal.getTime());
    }
    /**
     * 获取去年的开始时间
     * @return
     */
    public static Date getBeginDayOfLastYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear()-1);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);
        return getDayStartTime(cal.getTime());
    }
    /**
     * 获取去年的结束时间
     * @return
     */
    public static java.util.Date getEndDayOfLastYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear()-1);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DATE, 31);
        return getDayEndTime(cal.getTime());
    }

    /**
     * 获取前年的开始时间
     * @return
     */
    public static Date getBeginDayOfLastTwoYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear()-2);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);
        return getDayStartTime(cal.getTime());
    }
    /**
     * 获取前年的结束时间
     * @return
     */
    public static java.util.Date getEndDayOfLastTwoYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear()-2);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DATE, 31);
        return getDayEndTime(cal.getTime());
    }
    /**
     * 获取某个日期的开始时间
     * @param d
     * @return
     */
    public static Timestamp getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d)
            calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 获取某个日期的结束时间
     * @param d
     * @return
     */
    public static Timestamp getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d)
            calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 获取今年是哪一年
     * @return
     */
    public static Integer getNowYear() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return Integer.valueOf(gc.get(1));
    }

    /**
     * 获取本月是哪一月
     * @return
     */
    public static int getNowMonth() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(2) + 1;
    }

    /**
     * 两个日期相减得到的天数
     * @param beginDate
     * @param endDate
     * @return
     */
    public static int getDiffDays(Date beginDate, Date endDate) {
        if (beginDate == null || endDate == null) {
            throw new IllegalArgumentException("getDiffDays param is null!");
        }
        long diff = (endDate.getTime() - beginDate.getTime())
                / (1000 * 60 * 60 * 24);
        int days = new Long(diff).intValue();
        return days;
    }

    /**
     * 两个日期相减得到的毫秒数
     * @param beginDate
     * @param endDate
     * @return
     */
    public static long dateDiff(Date beginDate, Date endDate) {
        long date1ms = beginDate.getTime();
        long date2ms = endDate.getTime();
        return date2ms - date1ms;
    }

    /**
     * 获取两个日期中的最大日期
     * @param beginDate
     * @param endDate
     * @return
     */
    public static Date max(Date beginDate, Date endDate) {
        if (beginDate == null) {
            return endDate;
        }
        if (endDate == null) {
            return beginDate;
        }
        if (beginDate.after(endDate)) {
            return beginDate;
        }
        return endDate;
    }

    /**
     * 获取两个日期中的最小日期
     * @param beginDate
     * @param endDate
     * @return
     */
    public static Date min(Date beginDate, Date endDate) {
        if (beginDate == null) {
            return endDate;
        }
        if (endDate == null) {
            return beginDate;
        }
        if (beginDate.after(endDate)) {
            return endDate;
        }
        return beginDate;
    }

    /**
     * 返回某月该季度的第一个月
     * @param date
     * @return
     */
    public static Date getFirstSeasonDate(Date date) {
        final int[] SEASON = { 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4 };
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int sean = SEASON[cal.get(Calendar.MONTH)];
        cal.set(Calendar.MONTH, sean * 3 - 3);
        return cal.getTime();
    }

    /**
     * 返回某个日期下几天的日期
     * @param date
     * @param i
     * @return
     */
    public static Date getNextDay(Date date, int i) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + i);
        return cal.getTime();
    }

    /**
     * 返回某个日期前几天的日期
     * @param date
     * @param i
     * @return
     */
    public static Date getFrontDay(Date date, int i) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - i);
        return cal.getTime();
    }

    /**
     * 获取某年某月到某年某月按天的切片日期集合(间隔天数的集合)
     * @param beginYear
     * @param beginMonth
     * @param endYear
     * @param endMonth
     * @param k
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List getTimeList(int beginYear, int beginMonth, int endYear,
                                   int endMonth, int k) {
        List list = new ArrayList();
        if (beginYear == endYear) {
            for (int j = beginMonth; j <= endMonth; j++) {
                list.add(getTimeList(beginYear, j, k));
            }
        } else {
            {
                for (int j = beginMonth; j < 12; j++) {
                    list.add(getTimeList(beginYear, j, k));
                }
                for (int i = beginYear + 1; i < endYear; i++) {
                    for (int j = 0; j < 12; j++) {
                        list.add(getTimeList(i, j, k));
                    }
                }
                for (int j = 0; j <= endMonth; j++) {
                    list.add(getTimeList(endYear, j, k));
                }
            }
        }
        return list;
    }

    /**
     * 获取某年某月按天切片日期集合(某个月间隔多少天的日期集合)
     * @param beginYear
     * @param beginMonth
     * @param k
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static List getTimeList(int beginYear, int beginMonth, int k) {
        List list = new ArrayList();
        Calendar begincal = new GregorianCalendar(beginYear, beginMonth, 1);
        int max = begincal.getActualMaximum(Calendar.DATE);
        for (int i = 1; i < max; i = i + k) {
            list.add(begincal.getTime());
            begincal.add(Calendar.DATE, k);
        }
        begincal = new GregorianCalendar(beginYear, beginMonth, max);
        list.add(begincal.getTime());
        return list;
    }

}
