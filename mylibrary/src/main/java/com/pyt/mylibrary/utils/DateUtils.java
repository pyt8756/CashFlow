package com.pyt.mylibrary.utils;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 时间工具类
 */
@SuppressLint("SimpleDateFormat")
public class DateUtils {

    public static final String TIMEZONE_CN = "Asia/Shanghai";

    /**
     * Calendar 中对应的年
     */
    public final static int YEAR = Calendar.YEAR;
    /**
     * Calendar 中对应的月
     */
    public final static int MONTH = Calendar.MONTH;
    /**
     * Calendar 中对应的日
     */
    public final static int DAY = Calendar.DAY_OF_MONTH;
    /**
     * Calendar 中对应的时
     */
    public final static int HOUR = Calendar.HOUR_OF_DAY;
    /**
     * Calendar 中对应的分
     */
    public final static int MINUTE = Calendar.MINUTE;
    /**
     * Calendar 中对应的秒
     */
    public final static int SECOND = Calendar.SECOND;

    /**
     * 星期数组
     */
    private final static String[] WEEKNAME_CHINESE = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    // ================================SIMPLEFORMAT定义================
    /**
     ************** SIMPLEFORMATTYPE对就的类型定义****************** ＜p＞相应格式类型＜br＞
     */
    /**
     * @see #SIMPLEFORMATTYPESTRING1
     */
    public final static int SIMPLEFORMATTYPE1 = 0x01;
    /**
     * @see #SIMPLEFORMATTYPESTRING2
     */
    public final static int SIMPLEFORMATTYPE2 = 0x02;
    /**
     * @see #SIMPLEFORMATTYPESTRING3
     */
    public final static int SIMPLEFORMATTYPE3 = 0x03;
    /**
     * @see #SIMPLEFORMATTYPESTRING4
     */
    public final static int SIMPLEFORMATTYPE4 = 0x04;
    /**
     * @see #SIMPLEFORMATTYPESTRING5
     */
    public final static int SIMPLEFORMATTYPE5 = 0x05;
    /**
     * @see #SIMPLEFORMATTYPESTRING6
     */
    public final static int SIMPLEFORMATTYPE6 = 0x06;
    /**
     * @see #SIMPLEFORMATTYPESTRING7
     */
    public final static int SIMPLEFORMATTYPE7 = 0x07;
    /**
     * @see #SIMPLEFORMATTYPESTRING8
     */
    public final static int SIMPLEFORMATTYPE8 = 0x08;
    /**
     * @see #SIMPLEFORMATTYPESTRING9
     */
    public final static int SIMPLEFORMATTYPE9 = 0x09;
    /**
     * @see #SIMPLEFORMATTYPESTRING10
     */
    public final static int SIMPLEFORMATTYPE10 = 0x0a;
    /**
     * @see #SIMPLEFORMATTYPESTRING11
     */
    public final static int SIMPLEFORMATTYPE11 = 0x0b;
    /**
     * @see #SIMPLEFORMATTYPESTRING12
     */
    public final static int SIMPLEFORMATTYPE12 = 0x0c;
    /**
     * @see #SIMPLEFORMATTYPESTRING13
     */
    public final static int SIMPLEFORMATTYPE13 = 0x0d;
    /**
     * @see #SIMPLEFORMATTYPESTRING14
     */
    public final static int SIMPLEFORMATTYPE14 = 0x0e;
    /**
     * @see #SIMPLEFORMATTYPESTRING15
     */
    public final static int SIMPLEFORMATTYPE15 = 0x0f;
    /**
     * ********************SIMPLEFORMATTYPE对应的字串*********************
     */
    /**
     * SIMPLEFORMATTYPE1 对应类型：yyyyMMddHHmmss
     *
     * @see #SIMPLEFORMATTYPE1
     */
    public final static String SIMPLEFORMATTYPESTRING1 = "yyyyMMddHHmmss";

    /**
     * SIMPLEFORMATTYPE2 对应的类型：yyyy-MM-dd HH:mm:ss
     *
     * @see #SIMPLEFORMATTYPE2
     */
    public final static String SIMPLEFORMATTYPESTRING2 = "yyyy-MM-dd HH:mm:ss";

    /**
     * SIMPLEFORMATTYPE3 对应的类型：yyyy-M-d HH:mm:ss
     *
     * @see #SIMPLEFORMATTYPE3
     */
    public final static String SIMPLEFORMATTYPESTRING3 = "yyyy-M-d HH:mm:ss";

    /**
     * SIMPLEFORMATTYPE4对应的类型：yyyy-MM-dd HH:mm
     *
     * @see #SIMPLEFORMATTYPE4
     */
    public final static String SIMPLEFORMATTYPESTRING4 = "yyyy-MM-dd HH:mm";

    /**
     * SIMPLEFORMATTYPE5 对应的类型：yyyy-M-d HH:mm
     *
     * @see #SIMPLEFORMATTYPE5
     */
    public final static String SIMPLEFORMATTYPESTRING5 = "yyyy-M-d HH:mm";

    /**
     * SIMPLEFORMATTYPE6对应的类型：yyyyMMdd
     *
     * @see #SIMPLEFORMATTYPE6
     */
    public final static String SIMPLEFORMATTYPESTRING6 = "yyyyMMdd";

    /**
     * SIMPLEFORMATTYPE7对应的类型：yyyy-MM-dd
     *
     * @see #SIMPLEFORMATTYPE7
     */
    public final static String SIMPLEFORMATTYPESTRING7 = "yyyy-MM-dd";

    /**
     * SIMPLEFORMATTYPE8对应的类型： yyyy-M-d
     *
     * @see #SIMPLEFORMATTYPE8
     */
    public final static String SIMPLEFORMATTYPESTRING8 = "yyyy-M-d";

    /**
     * SIMPLEFORMATTYPE9对应的类型：yyyy年MM月dd日
     *
     * @see #SIMPLEFORMATTYPE9
     */
    public final static String SIMPLEFORMATTYPESTRING9 = "yyyy年MM月dd日";

    /**
     * SIMPLEFORMATTYPE10对应的类型：yyyy年M月d日
     *
     * @see #SIMPLEFORMATTYPE10
     */
    public final static String SIMPLEFORMATTYPESTRING10 = "yyyy年M月d日";

    /**
     * SIMPLEFORMATTYPE11对应的类型：M月d日
     *
     * @see #SIMPLEFORMATTYPE11
     */
    public final static String SIMPLEFORMATTYPESTRING11 = "M月d日";

    /**
     * SIMPLEFORMATTYPE12对应的类型：HH:mm:ss
     *
     * @see #SIMPLEFORMATTYPE12
     */
    public final static String SIMPLEFORMATTYPESTRING12 = "HH:mm:ss";

    /**
     * SIMPLEFORMATTYPE13对应的类型：HH:mm
     *
     * @see #SIMPLEFORMATTYPE13
     */
    public final static String SIMPLEFORMATTYPESTRING13 = "HH:mm";
    /**
     * SIMPLEFORMATTYPE7对应的类型：yyyy-MM-dd
     *
     * @see #SIMPLEFORMATTYPE14
     */
    public final static String SIMPLEFORMATTYPESTRING14 = "yyyy/MM/dd";

    /**
     * SIMPLEFORMATTYPE15对应的类型：MM月dd日
     *
     * @see #SIMPLEFORMATTYPE15
     */
    public final static String SIMPLEFORMATTYPESTRING15 = "MM月dd日";

    /**
     * 今天 ，明天，后天，数组
     */
    private final static String[] THREEDAYARR = new String[]{"今天", "明天", "后天"};//

    private static final String TAG = "DateUtils";

    /**
     * 将long类型转为yyyy-MM-dd的string
     */
    public static String formatDate(String date) {
        return formatDate(date, "yyyy-MM-dd");
    }


    /**
     * 获取 MM-dd日期
     */
    public static String formatSimpleDate(String date) {
        return formatDate(date, "MM-dd");
    }


    public static String formatDate(String date, String format) {
        try {
            return formatDate(Long.parseLong(date), format);
        } catch (Exception ex) {
            return "";
        }
    }

    public static String formatDate(long date) {
        return formatDate(date, "yyyy-MM-dd");

    }

    public static String formatDate(long date, String format) {
        try {
            SimpleDateFormat fm = new SimpleDateFormat(format);
            return fm.format(date);
        } catch (Exception ex) {
            return "";
        }
    }

    public static String formatDateT(String formatdate) {
        String beginDate = formatdate;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String sd = sdf.format(new Date(Long.parseLong(beginDate)));

        return sd;
    }

    /**
     * 计算所给的时间和当前日期的差
     */
    public static long getQuot(String inputTime) {
        long quot = 0;
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date2 = ft.parse(inputTime);
            Date date1 = new Date();
            quot = date1.getTime() - date2.getTime();
            quot = quot / 1000 / 60 / 60 / 24;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quot;
    }

    /**
     * 根据提供格式来转换时间字符串
     */
    public static long getDate(String inputTime, String format) {
        SimpleDateFormat ft = new SimpleDateFormat(format);
        Date date2 = null;
        try {
            date2 = ft.parse(inputTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date2.getTime();
    }

    /**
     * 计算折扣
     */
    public static String compute(String discount, String price) {
        if (!StringUtils.isEmpty(discount)) {
            Float dis = new Float(discount);
            float pri = Float.parseFloat(price);
            float result = 0;
            if (dis == 0.0) {
                result = pri;
            } else {
                result = dis / 10 * pri;
            }
            return String.format("%.2f", result);
        } else {
            return price;
        }

    }

    /**
     * 获取当前日期 yyyyMMddHHmmss 14位
     */
    public static String getCurrentTime() {
        Calendar currentCalendar = Calendar.getInstance(TimeZone
                .getTimeZone(TIMEZONE_CN));
        return getCalendarStrBySimpleDateFormat(currentCalendar,
                SIMPLEFORMATTYPE1);
    }

    /**
     * 获取当前日期 8位
     */
    public static String getCurrentDate() {
        Calendar currentCalendar = Calendar.getInstance(TimeZone
                .getTimeZone(TIMEZONE_CN));
        return getCalendarStrBySimpleDateFormat(currentCalendar,
                SIMPLEFORMATTYPE7);
    }

    /**
     * 获取手机当前日期
     */
    public static Calendar getLocalCalendar() {
        Calendar localCalendar = Calendar.getInstance(TimeZone
                .getTimeZone(TIMEZONE_CN));
        return localCalendar;
    }

    /**
     * 将日期字串转为 Calendar ,dateStr需超过8位且不能为空,否则返回null
     */
    public static Calendar getCalendarByDateStr(String dateStr) {
        if (StringUtils.isEmpty(dateStr) || dateStr.length() < 8)
            return null;
        Calendar calendar = getLocalCalendar();
        int year = Integer.valueOf(dateStr.substring(0, 4));
        int month = Integer.valueOf(dateStr.substring(4, 6));
        int day = Integer.valueOf(dateStr.substring(6, 8));
        calendar.set(year, month - 1, day, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    /**
     * 将时间字串转为 Calendar dateStr小于8时返回null，不足14位补0
     */
    public static Calendar getCalendarByDateTimeStr(String dateStr) {
        if (StringUtils.isEmpty(dateStr) || dateStr.length() < 8)
            return null;
        while (dateStr.length() < 14) {
            dateStr += "0";
        }
        Calendar calendar = getLocalCalendar();
        // LogUtil.e("dateStr" + dateStr);
        int year = StringUtils.toInt(dateStr.substring(0, 4));
        int month = StringUtils.toInt(dateStr.substring(4, 6));
        int day = StringUtils.toInt(dateStr.substring(6, 8));
        int hour = StringUtils.toInt(dateStr.substring(8, 10));
        int min = StringUtils.toInt(dateStr.substring(10, 12));
        int second = 0;
        if (dateStr.length() >= 14)
            second = StringUtils.toInt(dateStr.substring(12, 14));
        calendar.set(year, month - 1, day, hour, min, second);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    /**
     * 当前日期前一天 yyyyMMdd
     */
    public static String getLastDate() {
        Calendar calendar = Calendar.getInstance(TimeZone
                .getTimeZone(TIMEZONE_CN));
        calendar.add(Calendar.DATE, -1);
        return getCalendarStrBySimpleDateFormat(calendar, SIMPLEFORMATTYPE6);
    }

    /**
     * @param type =1今天2昨天,3前天
     */
    public static String getDate(int type, int style) {
        Calendar calendar = Calendar.getInstance(TimeZone
                .getTimeZone(TIMEZONE_CN));
        if (type == 1) {

        } else if (type == 2) {
            calendar.add(Calendar.DATE, -1);
        } else if (type == 3) {
            calendar.add(Calendar.DATE, -2);
        }

        return getCalendarStrBySimpleDateFormat(calendar, style);
    }

    /**
     * 当前日期后一天 yyyyMMdd
     */
    public static String getNextDate() {
        Calendar calendar = Calendar.getInstance(TimeZone
                .getTimeZone(TIMEZONE_CN));
        calendar.add(Calendar.DATE, 1);
        return getCalendarStrBySimpleDateFormat(calendar, SIMPLEFORMATTYPE6);
    }

    /**
     * 根据日期返回对应的星期
     *
     * @param calendar if calendar 为null则返回空字串
     * @return "星期*"
     * @see #WEEKNAME_CHINESE
     * @see #getWeek
     */
    public static String getShowWeekByCalendar(Calendar calendar) {
        String weekString = "";
        if (calendar == null) {
            return weekString;
        }
        if (getWeek(calendar) != -1) {
            weekString = WEEKNAME_CHINESE[getWeek(calendar)];
        }
        return weekString;
    }

    /**
     * 星期几, 数字表述. 从周日开始
     *
     * @param calendar if calendar 为null则返回-1
     */
    public static int getWeek(Calendar calendar) {
        int theResult = -1;
        if (calendar != null) {
            theResult = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return theResult;
    }

    /**
     * 返回，今天/明天/后天
     *
     * @param count 0，1，2 其他则返回空字串
     */
    public static String getThreeDayDes(int count) {
        if (count >= 0 && count <= 2) {
            return THREEDAYARR[count];
        }
        return "";
    }

    /**
     * 获取calendar对应的年
     *
     * @return 年字段值
     */
    public static int getYear(Calendar calendar) {
        return calendar.get(YEAR);
    }

    /**
     * 获取calendar对应的月份
     */
    public static int getMonth(Calendar calendar) {
        return calendar.get(MONTH) + 1;
    }

    /**
     * 获取calendar对应的DAY
     */
    public static int getDay(Calendar calendar) {
        return calendar.get(DAY);
    }

    /**
     * 获取calendar对应的小时
     */
    public static int getHourOfDay(Calendar calendar) {
        return calendar.get(HOUR);
    }

    /**
     * 获取calendar对应的分钟
     */
    public static int getMinute(Calendar calendar) {
        return calendar.get(MINUTE);
    }

    /**
     * 获取calendar对应的秒
     */
    public static int getSecond(Calendar calendar) {
        return calendar.get(SECOND);
    }

    /**
     * 计算日期字串 DAY字段
     */
    public static String getDateByStep(String date, int step) {
        if (StringUtils.isEmpty(date) || date.length() < 8)
            return "";
        else if (step == 0)
            return date;
        else {
            return getCalendarStrBySimpleDateFormat(
                    calculateCalendar(getCalendarByDateStr(date), DAY, step),
                    SIMPLEFORMATTYPE6);
        }
    }

    /**
     * 对14位的日期对应的字段进行加减计算 并返回yyyyMMddHHmmss
     *
     * @param date  14位日期字串
     * @param field Calendar对应的年、月、日 属性
     * @return 计算过后的日期字串 yyyyMMddHHmmss
     */
    public static String getTimeByStep(String date, int field, int step) {
        if (StringUtils.isEmpty(date) || date.length() < 14)
            return "";
        else if (step == 0)
            return date;
        else {
            return getCalendarStrBySimpleDateFormat(
                    calculateCalendar(getCalendarByDateTimeStr(date), field,
                            step), SIMPLEFORMATTYPE1);
        }
    }

    /**
     * 日期加减
     */
    public static Calendar calculateCalendar(Calendar newcalendar, int field,
                                             int amount) {
        Calendar calendar = (Calendar) newcalendar.clone();
        if (calendar == null) {
            return null;
        }
        switch (field) {
            case YEAR:
                calendar.add(YEAR, amount);
                break;
            case MONTH:
                calendar.add(MONTH, amount);
                break;
            case DAY:
                calendar.add(DAY, amount);
                break;
            case HOUR:
                calendar.add(HOUR, amount);
                break;
            case MINUTE:
                calendar.add(MINUTE, amount);
                break;
            case SECOND:
                calendar.add(SECOND, amount);
                break;
        }
        return calendar;
    }

    /**
     * dateStr小于8时返回null，不足14位补0 将dateStr 转换成 SimpleDateFormatType对应格式的字串
     */
    public static String CalendarStrBySimpleDateFormat(String dateStr,
                                                       int SimpleDateFormatType) {
        return getCalendarStrBySimpleDateFormat(
                getCalendarByDateTimeStr(dateStr), SimpleDateFormatType);
    }

    /**
     * 根据 SimpleDateFormatType类型将calendar转成对应的格式 如果calendar 为null则返回“”
     *
     * @param calendar             日历
     * @param SimpleDateFormatType 需要转换的格式类型
     */
    public static String getCalendarStrBySimpleDateFormat(Calendar calendar,
                                                          int SimpleDateFormatType) {
        String str = "";
        String type = "";
        switch (SimpleDateFormatType) {
            case SIMPLEFORMATTYPE1:
                type = SIMPLEFORMATTYPESTRING1;
                break;
            case SIMPLEFORMATTYPE2:
                type = SIMPLEFORMATTYPESTRING2;
                break;
            case SIMPLEFORMATTYPE3:
                type = SIMPLEFORMATTYPESTRING3;
                break;
            case SIMPLEFORMATTYPE4:
                type = SIMPLEFORMATTYPESTRING4;
                break;
            case SIMPLEFORMATTYPE5:
                type = SIMPLEFORMATTYPESTRING5;
                break;
            case SIMPLEFORMATTYPE6:
                type = SIMPLEFORMATTYPESTRING6;
                break;
            case SIMPLEFORMATTYPE7:
                type = SIMPLEFORMATTYPESTRING7;
                break;
            case SIMPLEFORMATTYPE8:
                type = SIMPLEFORMATTYPESTRING8;
                break;
            case SIMPLEFORMATTYPE9:
                type = SIMPLEFORMATTYPESTRING9;
                break;
            case SIMPLEFORMATTYPE10:
                type = SIMPLEFORMATTYPESTRING10;
                break;
            case SIMPLEFORMATTYPE11:
                type = SIMPLEFORMATTYPESTRING11;
                break;
            case SIMPLEFORMATTYPE12:
                type = SIMPLEFORMATTYPESTRING12;
                break;
            case SIMPLEFORMATTYPE13:
                type = SIMPLEFORMATTYPESTRING13;
                break;
            case SIMPLEFORMATTYPE14:
                type = SIMPLEFORMATTYPESTRING14;
                break;
            case SIMPLEFORMATTYPE15:
                type = SIMPLEFORMATTYPESTRING15;
                break;
            default:
                type = SIMPLEFORMATTYPESTRING1;
                break;
        }
        if (!StringUtils.isEmpty(type) && calendar != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(type);
            dateFormat.setTimeZone(TimeZone.getTimeZone(TIMEZONE_CN));
            str = (dateFormat).format(calendar.getTime());
        }
        return str;
    }

    /**
     * 将yyyyMMdd字符串转成yyyy年M月d日 星期x格式
     *
     * @return 如果 dateStr为空则返回“”
     */
    public static String getShowWeekByDate(String dateStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return "";
        }
        Calendar currentDate = getCalendarByDateStr(dateStr);
        String date = getCalendarStrBySimpleDateFormat(currentDate,
                SIMPLEFORMATTYPE10);
        StringBuffer showStringBuffer = new StringBuffer(18);
        showStringBuffer.append(date);
        String weekString = getShowWeekByCalendar(currentDate);
        showStringBuffer.append("  " + weekString);
        return showStringBuffer.toString().trim();
    }

    /**
     * 若计算datestr与当前日期相差在3天内，将yyyyMMdd字符串转成"今天/明天/后天/" 否则返回""
     *
     * @return 今天/明天/后天*
     */
    public static String getDateStrCompareToDay(String dateStr) {
        String today = "";
        String ctripDateStr = getCurrentTime(); // 获取当前日期字串
        // 计算datestr与当前日期相差几天
        long ctripDateM = getCalendarByDateStr(ctripDateStr).getTimeInMillis();
        long dateM = getCalendarByDateStr(dateStr).getTimeInMillis();
        long dValue = dateM - ctripDateM;
        int dDay = (int) (dValue / (24 * 60 * 60 * 1000));
        // dDay在3天内则返回相应的今天/明天/后天
        if (dDay >= 0 && dDay <= 2) {
            today += getThreeDayDes(dDay);
        }
        return today;
    }

    /**
     * 将yyyyMMdd字符串转成yyyy-MM-dd
     */
    public static String getShowWeekByDate5(String dateStr) {
        String str = getCalendarStrBySimpleDateFormat(
                getCalendarByDateStr(dateStr), SIMPLEFORMATTYPE7);
        return str.toString().trim();
    }

    /**
     * 将时间字串进行转换 取掉:号，并将每段补齐成两位显示
     *
     * @param d 00:00格式
     * @return 000000
     */
    public static String parseTude(String d) {
        String r = "";
        if (d != null && !"".equals(d)) {
            int i = d.indexOf(":");
            r += d.substring(0, i);
            if (r.length() == 1) {
                r = "0" + r;
            }
            String ss = d.substring(i + 1, d.length());
            if (ss.length() == 1) {
                ss = "0" + ss;
            }
            r = r + ss + "00";
        }
        return r;
    }

    /**
     * 给日期 字符串中的（今天、明天、后天）上色
     */
    public static SpannableStringBuilder colorDate(String dateStr) {
        if (dateStr.indexOf("今天") > 0 || dateStr.indexOf("明天") > 0
                || dateStr.indexOf("后天") > 0) {
            SpannableStringBuilder styleStr = new SpannableStringBuilder(
                    dateStr);
            styleStr.setSpan(new ForegroundColorSpan(Color.rgb(209, 0, 8)), 12,
                    14, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return styleStr;
        }
        return null;
    }

    /**
     * 将yyyyMMdd字符串转成yyyy年MM月dd日 格式
     */
    public static String getShowStrByDateStr(String dateStr) {
        Calendar currentDate = getCalendarByDateStr(dateStr);
        if (currentDate == null) {
            return "";
        }
        int year = currentDate.get(Calendar.YEAR);
        int month = currentDate.get(Calendar.MONTH) + 1;
        int day = currentDate.get(Calendar.DAY_OF_MONTH);
        StringBuffer showStringBuffer = new StringBuffer(18);
        showStringBuffer.append(year);
        showStringBuffer.append("年");
        showStringBuffer.append(month);
        showStringBuffer.append("月");
        showStringBuffer.append(day);
        showStringBuffer.append("日");
        return showStringBuffer.toString().trim();
    }

    /**
     * 根据14位日期字符串返回00:00这样的时间字符串,空则返回""
     */
    public static String getTimeStrFromDateString(String date) {
        Calendar temp = getCalendarByDateTimeStr(date);
        if (temp == null) {
            return "";
        }
        return getCalendarStrBySimpleDateFormat(temp, SIMPLEFORMATTYPE13);

    }

    /**
     * 根据8位日期字符串返回x月x日这样的日期字符串,空则返回""
     */
    public static String getSimpleDateStrFromDateString(String date) {
        Calendar temp = getCalendarByDateStr(date);
        if (temp == null) {
            return "";
        }
        return getCalendarStrBySimpleDateFormat(temp, SIMPLEFORMATTYPE11);

    }

    @SuppressWarnings("deprecation")
    public static String convertNewsTime(long milliseconds) {
        long subtime = System.currentTimeMillis() - milliseconds;
        Date taday = new Date(System.currentTimeMillis());
        Date cacheDay = new Date(milliseconds);
        if (cacheDay.after(taday))
            return convertSecondsToMMDDString(milliseconds);// 如果手机时间调整为之前时间
        // 则显示上传时间
        if (taday.getYear() == cacheDay.getYear()) {
            if (taday.getMonth() == cacheDay.getMonth()
                    && taday.getDate() == cacheDay.getDate()) {
                if (0 <= subtime && subtime < 60 * 1000) {
                    return "刚刚";
                } else if (1L * 60 * 1000 <= subtime
                        && subtime < 1L * 60 * 60 * 1000) {
                    return subtime / (60 * 1000) + "分钟前";
                } else if (1L * 60 * 60 * 1000 <= subtime
                        && subtime < 1L * 24 * 60 * 60 * 1000) {
                    return subtime / (60 * 60 * 1000) + "小时前";
                }
            }
            return convertSecondsToMMDDString(milliseconds);// x月x日
        }
        return convertSecondsToYYYYString(milliseconds);// xxxx年
    }

    public static String convertSecondsToMMDDString(long milliseconds) {
        SimpleDateFormat fn = new SimpleDateFormat("MM月dd日");
        String str = fn.format(new Date(milliseconds));
        return str;
    }

    public static String convertSecondsToYYYYString(long milliseconds) {
        SimpleDateFormat fn = new SimpleDateFormat("yyyy年");
        String str = fn.format(new Date(milliseconds));
        return str;
    }

    public static int getYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

    public static int getMonth() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH) + 1;
    }

    public static int getDay() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static int getYear(long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        return c.get(Calendar.YEAR);
    }

    public static int getMonth(long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        return c.get(Calendar.MONTH) + 1;
    }

    public static int getDay(long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取每个月多少天
     */
    public static int getMonthLength(int year, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                if (isLeapYear(year)) {
                    return 29;
                } else {
                    return 28;
                }
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
        }
        return 0;
    }

    /**
     * 是否闰年
     *
     * @return true:闰年
     */
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }
}
