package com.common.util;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 时间工具类
 *
 * @ClassName: DateUtil
 * @author: lishuhan
 * @date 2018-5-30 11:04:17
 */
public class DateUtil {
    public final static SimpleDateFormat g_SimpleDateFormat = new SimpleDateFormat(
            "yyyyMMdd");
    public final static SimpleDateFormat g_SimpleDateFormat_I = new SimpleDateFormat(
            "yyyy-MM-dd");
    public final static SimpleDateFormat g_SimpleDateFormat_II = new SimpleDateFormat(
            "yyyyMM");
    public final static SimpleDateFormat sdfDateTimeFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    public final static SimpleDateFormat sdfDateTimeFormat_I = new SimpleDateFormat(
            "yyyyMMddHHmmss");
    public final static SimpleDateFormat sdfDateTimeFormat_IIII = new SimpleDateFormat(
            "HH:mm:ss");
    public final static SimpleDateFormat sdfDateTimeFormat_YYYY = new SimpleDateFormat(
            "yyyy");
    public final static SimpleDateFormat x_dateTimeFormat = new SimpleDateFormat(
            "yyyy/MM/dd HH:mm:ss");

    public final static SimpleDateFormat xISO_dateTimeFormat = new SimpleDateFormat(
            "yyyy/MM/dd HH:mm:ss:SSS");

    /**
     * 使用参数Format格式化Date成字符串
     */
    public static String format(Date date, SimpleDateFormat pattern) {
        return date == null ? " " : pattern.format(date);
    }

    /**
     * 使用参数Format将字符串转为Date
     */
    public static Date parse(String strDate, SimpleDateFormat pattern)
            throws ParseException {
        return StringUtils.isBlank(strDate) ? null : pattern.parse(strDate);
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * 返回当前时间日期格式(yyyy-MM-dd HH:mm:ss)
     *
     * @return
     */
    public static String getCurDateTime() {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        return (sdf.format(now.getTime()));
    }

    /**
     * 返回当前时间日期格式时间戳(yyyyMMddHHmmss)
     *
     * @return
     */
    public static String getCurDateTimeYMDHMS() {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        String DATE_FORMAT = "yyyyMMddHHmmss";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        return (sdf.format(now.getTime()));
    }

    public static String getCurrentDateM() {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        return (sdf.format(now.getTime()));
    }

    /**
     * 返回当前时间 格式（yyyy-MM-dd）
     */
    public static String getCurDateYMR() {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        String DATE_FORMAT = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        return (sdf.format(now.getTime()));
    }

    /**
     * 返回日期格式(yyyy-MM-dd HH:mm:ss)
     *
     * @param date
     * @return
     */
    public static String getDateTime(Date date) {
        return sdfDateTimeFormat.format(date);
    }

    /**
     * 返回日期格式(yyyyMMdd)
     *
     * @param date
     * @return
     */
    public static String getDateTime2(Date date) {
        return g_SimpleDateFormat.format(date);
    }

    /**
     * 返回日期格式(yyyy-MM-dd)
     *
     * @param date
     * @return
     */
    public static String getDateTime_I(Date date) {
        return g_SimpleDateFormat_I.format(date);
    }

    /**
     * 返回日期格式(yyyy-MM-dd HH:mm:ss)
     *
     * @param date
     * @return
     */
    public static String getDateTime_III(Date date) {
        return sdfDateTimeFormat.format(date);
    }

    /**
     * <p>返回日期格式(yyyy-MM-dd HH:mm:ss)</p>
     * <br>
     *
     * @return
     */
    public static String getCurrDateTime() {
        return sdfDateTimeFormat.format(new Date());
    }

    /**
     * 返回时间格式(HH:mm:ss)
     *
     * @param date
     * @return
     */
    public static String getDateTime_IIII(Date date) {
        return sdfDateTimeFormat_IIII.format(date);
    }

    /**
     * 返回日期格式(yyyyMMddHHmmss)
     *
     * @param date
     * @return
     */
    public static String getDateTime_IV(Date date) {
        return sdfDateTimeFormat_I.format(date);
    }

    /**
     * 获取当前日期(yyyyMMdd)
     *
     * @return
     */
    public static String getCurDate() {
        return g_SimpleDateFormat.format(new Date());
    }

    /**
     * <p>
     * 获取当前系统时间的小时数
     * </p>
     *
     * @return
     */
    public static int getCurrentHour() {
        Calendar calendar = new GregorianCalendar();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 得到当前时间分钟
     *
     * @return
     */
    public static int getCurrentMinute() {
        Calendar calendar = new GregorianCalendar();
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * <p>
     * 获取当前年月格式(yyyyMM)
     * </p>
     *
     * @return
     */
    public static String getCurrentMonth() {
        Calendar calendar = new GregorianCalendar();
        return g_SimpleDateFormat_II.format(calendar.getTime());
    }

    public static String getMonth(Date date) {
        return g_SimpleDateFormat_II.format(date);
    }

    /**
     * 获得本月的前（后）几个月。(yyyyMM)
     * <p>
     * %方法详述（简单方法可不必详述）%。
     * </p>
     *
     * @param monthNum 月数字(数字大于1为后，小于1为前)
     * @return
     */
    public static String getBAMonth(int monthNum) {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MONTH, monthNum);
        return g_SimpleDateFormat_II.format(calendar.getTime());
    }

    /**
     * <p>
     * 获取本月第一天日期（格式如YYYYMMDD）,如果当前日为当月1日,则返回上月第一日
     * </p>
     *
     * @return
     */
    public static String getMonthFirstDay() {
        Calendar calendar = new GregorianCalendar();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = 0;
        if (day == 1)// 当月第一日
        {
            calendar.add(Calendar.MONTH, -1);
        }
        month = calendar.get(Calendar.MONTH);
        if (month < 10) {
            return "" + calendar.get(Calendar.YEAR) + "0" + (month + 1) + "01";
        } else {
            return "" + calendar.get(Calendar.YEAR) + (month + 1) + "01";
        }
    }

    /**
     * <p>
     * 获取当前时间前几天或后几天的日期
     * </p>
     * 日数字(数字大于1为后，小于1为前)
     *
     * @return
     */
    public static Date getAddDays(int days) {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    /**
     * <p>
     * 获取某个月后的日期格式（yyyyMMdd）
     * </p>
     *
     * @return
     */
    public static String getAfterMonth(int monthNum) {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MONTH, monthNum);
        return g_SimpleDateFormat.format(calendar.getTime());
    }

    /**
     * <p>
     * 返回日期（格式yyyyMMdd）
     * </p>
     *
     * @param timeMillis
     * @return
     */
    public static String getFormatDate(long timeMillis) {
        return sdfDateTimeFormat_I.format(new Date(timeMillis));
    }

    /**
     * 获取当前系统时间距离传入时间的毫秒数
     *
     * @param strTime 格式[ DD:00:00]
     * @return
     * @throws ParseException
     */
    public static long getSleepTime(String strTime) throws ParseException {
        long p = 1;
        long l_date = System.currentTimeMillis();
        Date date_now = new Date(l_date);
        String strDate = g_SimpleDateFormat_I.format(date_now) + strTime;
        if (date_now.before(sdfDateTimeFormat.parse(strDate)))
            p = (sdfDateTimeFormat.parse(strDate)).getTime() - l_date;
        else {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date_now);
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            Date date = calendar.getTime();
            strDate = g_SimpleDateFormat_I.format(date) + strTime;
            p = (sdfDateTimeFormat.parse(strDate)).getTime() - l_date;
        }
        return p;
    }

    /**
     * 返回当前日期的前一天 返回格式（YYYY-mm-dd）
     *
     * @return
     */
    public static String getPredate() {
        Date nowDate = new Date();
        String nowdates = g_SimpleDateFormat_I.format(nowDate);
        String[] dates = nowdates.split("-");
        int year = Integer.parseInt(dates[0]);
        int month = Integer.parseInt(dates[1]);
        int day = Integer.parseInt(dates[2]) - 1;
        if (day == 0) {
            switch (month - 1) {
                case 1:
                    day = 31;
                    break;
                case 3:
                    day = 31;
                    break;
                case 5:
                    day = 31;
                    break;
                case 7:
                    day = 31;
                    break;
                case 8:
                    day = 31;
                    break;
                case 10:
                    day = 31;
                    break;
                case 0:
                    month = 13;
                    year = year - 1;
                    day = 31;
                    break;
                case 4:
                    day = 30;
                    break;
                case 6:
                    day = 30;
                    break;
                case 9:
                    day = 30;
                    break;
                case 11:
                    day = 30;
                    break;
                case 2:
                    if (year % 4 == 0) {
                        day = 29;
                    } else {
                        day = 28;
                    }
                    break;
                default:
                    break;
            }
            month = month - 1;
        }
        String predate = Integer.toString(year) + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
        return predate;
    }

    /**
     * add by hushiwang
     * <p>
     * 得到xxxx年xx月xx日 日期格式。
     * </p>
     *
     * @param date
     * @return
     */
    public static String getChinaDateFormat(Date date) {
        // 得到yyyy-mm-dd格式日期格式
        String dateStr = getDateTime_I(date);
        StringBuffer sb = new StringBuffer();
        if (dateStr != null && dateStr.length() > 0) {
            String[] newStr = dateStr.split("-");
            // 得到月
            Integer month = Integer.valueOf(newStr[1]);
            // 得到日
            Integer day = Integer.valueOf(newStr[2]);
            sb.append(newStr[0]).append("年");
            sb.append(month).append("月").append(day).append("日");
        }
        return sb.toString();
    }

    /**
     * add by wangzs
     * <p>
     * 得到xxxx年xx月xx日xx时xx分日期格式。
     * </p>
     *
     * @param date
     * @return
     */
    public static String getChinaDateFormat_II(Date date) {
        // 得到yyyy-mm-dd HH:mm:ss格式日期格式
        String dateStr = getDateTime(date);
        StringBuffer sb = new StringBuffer();
        if (dateStr != null && dateStr.length() > 0) {
            String[] str = dateStr.split(" ");
            if (str != null && str.length > 1) {
                String[] newStr = str[0].split("-");
                // 得到月
                Integer month = Integer.valueOf(newStr[1]);
                // 得到日
                Integer day = Integer.valueOf(newStr[2]);
                sb.append(newStr[0]).append("年");
                sb.append(month).append("月").append(day).append("日");
                String[] newStr_II = str[1].split(":");
                sb.append(newStr_II[0]).append("时").append(newStr_II[1]).append("分");
            }
        }
        return sb.toString();
    }

    /**
     * add by hushiwang
     * <p>
     * 得到xxxx年xx月xx日 日期格式。
     * </p>
     *
     * @param date 格式必须是2009-03-21字符串
     * @return
     */
    public static String getChinaDateFormat(String date) {
        // 得到yyyy-mm-dd格式日期格式
        StringBuffer sb = new StringBuffer();
        if (date != null && date.length() > 0) {
            String[] newStr = date.split("-");
            // 得到月
            Integer month = Integer.valueOf(newStr[1]);
            // 得到日
            Integer day = Integer.valueOf(newStr[2]);
            sb.append(newStr[0]).append("年");
            sb.append(month).append("月").append(day).append("日");
        }
        return sb.toString();
    }

    /**
     * 判断一个日期字符串是否合法
     *
     * @param date
     * @param format
     * @return
     */
    public static boolean isDateStringCorrect(String date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);

        try {
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * <p>
     * 将字符串类型的日期格式 转换为 符合要求的日期格式
     * </P>
     *
     * @param date
     * @param format
     * @return
     */
    public static String getStrDate4String(String date, String format) {
        if (date == null || date.trim().equals("")) {
            return "";
        } else {
            SimpleDateFormat df = new SimpleDateFormat(format);
            try {
                Date d = df.parse(date);
                return df.format(d);
            } catch (ParseException e) {
                System.out.println(e);
                return "";
            }
        }
    }

    /**
     * <p>
     * 将Date类型的日期格式 转换为 符合要求的 String日期格式
     * </P>
     *
     * @param date
     * @param format
     * @return
     */
    public static String getStrDate4Date(Date date, String format) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat df = new SimpleDateFormat(format);
            return df.format(date);
        }
    }

    /**
     * <p>
     * 将字符串类型的日期格式 转换为 符合要求的 Date类型的日期格式
     * </P>
     *
     * @param date
     * @param format
     * @return
     */
    public static Date getDate4StrDate(String date, String format) {
        if (date == null || date.trim().equals("")) {
            return null;
        } else {
            SimpleDateFormat df = new SimpleDateFormat(format);
            try {
                return df.parse(date);
            } catch (ParseException e) {
                return null;
            }
        }
    }

    /**
     * 计算指定日期时间之间的时间差
     *
     * @param beginStr 开始日期字符串
     * @param endStr   结束日期字符串
     * @param f        时间差的形式0-秒,1-分种,2-小时,3--天 日期时间字符串格式:yyyyMMddHHmmss
     */
    public static int getInterval(String beginStr, String endStr, int f) {
        int hours = 0;
        try {
            Date beginDate = sdfDateTimeFormat.parse(beginStr);
            Date endDate = sdfDateTimeFormat.parse(endStr);
            long millisecond = endDate.getTime() - beginDate.getTime(); // 日期相减得到日期差X(单位:毫秒)
            /**
             * Math.abs((int)(millisecond/1000)); 绝对值 1秒 = 1000毫秒
             * millisecond/1000 --> 秒 millisecond/1000*60 - > 分钟
             * millisecond/(1000*60*60) -- > 小时 millisecond/(1000*60*60*24) -->
             * 天
             * */
            switch (f) {
                case 0: // second
                    return (int) (millisecond / 1000);
                case 1: // minute
                    return (int) (millisecond / (1000 * 60));
                case 2: // hour
                    return (int) (millisecond / (1000 * 60 * 60));
                case 3: // day
                    return (int) (millisecond / (1000 * 60 * 60 * 24));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hours;
    }

    /**
     * <p>
     * 得到起始日期前或后天数的日期
     * </P>
     *
     * @param starttime 起始日期 格式：yyyy-MM-dd
     * @param
     * @return
     * @throws ParseException
     */
    public static String getStartDateInterval(String starttime, int mins) {
        // 格式化起始时间 yyyyMMdd
        Date startDate = null;
        try {
//            startDate = sdfDateTimeFormat.parse(starttime);
            startDate = g_SimpleDateFormat_I.parse(starttime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar startTime = Calendar.getInstance();
        startTime.clear();
        startTime.setTime(startDate);

        startTime.add(Calendar.MINUTE, mins);

        return getDateTime_III(startTime.getTime());
    }

    /**
     * <p>
     * 得到起始日期和结束日期之间的天数
     * </P>
     *
     * @param beginStr 起始日期
     * @param endStr   结束日期
     * @param format   根据 日期参数的格式，传对应的SimpleDateFormat格式
     * @return 天数
     */
    public static int getDaysInterval(String beginStr, String endStr,
                                      SimpleDateFormat format) {

        try {
            Date beginDate = format.parse(beginStr);
            Date endDate = format.parse(endStr);
            long millisecond = endDate.getTime() - beginDate.getTime(); // 日期相减得到日期差X(单位:毫秒)
            return (int) (millisecond / (1000 * 60 * 60 * 24));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 得到某个时间当天的最后时间 （时分秒为 23:59:59形式)
     */
    public static Date getEndDate(Date date) {
        if (date != null) {
            //将截止时间设为指定日期的23:59:59
            date.setHours(23);
            date.setMinutes(59);
            date.setSeconds(59);
        }
        return date;
    }

    /**
     * 得到本月第一天
     *
     * @return
     */
    public static Date getFristDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return calendar.getTime();

    }

    /**
     * 得到本月最后一天
     *
     * @return
     */
    public static Date getLastDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return calendar.getTime();

    }


    /**
     * 得到当前年份
     *
     * @return
     */
    public static int getCurrentYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

    /**
     * 得到当前月份
     *
     * @return
     */
    public static int getMonth() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 得到当前日
     *
     * @return
     */
    public static int getDayOfMonth() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * <p>获得指定的年，int格式</p>
     * <br>
     *
     * @param date
     * @return
     */
    public static int getCustomYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * <p>获得指定的月，int格式</p>
     * <br>
     *
     * @param date
     * @return
     */
    public static int getCustomMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * <p>获得指定的日(月份中的)，int格式</p>
     * <br>
     *
     * @param date
     * @return
     */
    public static int getCustomDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * <p>获得指定的小时(日中的)，int格式</p>
     * <br>
     *
     * @return
     */
    public static int getCustomHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * <p>获得指定的分钟，int格式</p>
     * <br>
     *
     * @return
     */
    public static int getCustomMinute(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    /**
     * <p>获得指定的秒，int格式</p>
     * <br>
     *
     * @return
     */
    public static int getCustomSecond(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.SECOND);
    }

    /**
     * @param beginDate
     * @param endDate
     * @param f         时间差的形式0:秒,1:分种,2:小时,3:天
     * @return
     */
    public static int getDifferenceNum(Date beginDate, Date endDate, int f) {
        int result = 0;
        if (beginDate == null || endDate == null) {
            return 0;
        }
        try {
            // 日期相减得到日期差X(单位:毫秒)
            long millisecond = endDate.getTime() - beginDate.getTime();
            /**
             * Math.abs((int)(millisecond/1000)); 绝对值 1秒 = 1000毫秒
             * millisecond/1000 --> 秒 millisecond/1000*60 - > 分钟
             * millisecond/(1000*60*60) -- > 小时 millisecond/(1000*60*60*24) -->
             * 天
             * */
            switch (f) {
                case 0: // second
                    return (int) (millisecond / 1000);
                case 1: // minute
                    return (int) (millisecond / (1000 * 60));
                case 2: // hour
                    return (int) (millisecond / (1000 * 60 * 60));
                case 3: // day
                    return (int) (millisecond / (1000 * 60 * 60 * 24));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * <p>比较两个日期的大小,精确到秒</p>
     *
     * @param d1
     * @param d2
     * @return 返回一个long类型的整数，若大于0表示第一个日期晚于第二个日期，小于0表示第一个日期早于第二个日期，否则相等
     * @date 2016-11-22
     */
    public static long compareEachOther(Date d1, Date d2) {
        if (d1 == null || d2 == null)
            return -1;
        String d1Str = d1.getTime() + "";
        String d2Str = d2.getTime() + "";
        int l1 = d1Str.length();
        int l2 = d2Str.length();
        d1Str = d1Str.substring(0, l1 - 3) + "000";
        d2Str = d2Str.substring(0, l2 - 3) + "000";
        //System.out.println(d1Str + "   " + d2Str);
        long long1 = Long.parseLong(d1Str);
        long long2 = Long.parseLong(d2Str);
        return long1 - long2;
    }

    /**
     * 返回日期格式(yyyy-MM-dd HH:mm:ss Z)
     *
     * @param date
     * @return
     */
    public static String getDateTimeZ(Date date) {
        return xISO_dateTimeFormat.format(date);
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 年数差
     *
     * @param date
     * @param i
     * @return
     */
    public static Date getBeforeYear(Date date, int i) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.YEAR, i);
        date = calendar.getTime();
        return date;
    }

    public static void main(String[] args) throws ParseException, InterruptedException {
//for(int i=0;i<10;i++){
//    System.out.println(getCurrentDateM());
//
//}
//      //获取当前日期
//      System.out.println(getCurrentDate());
//      //返回当前时间日期格式(yyyyMMddHHmmss)
//      System.out.println(getCurDateTime());
//      //返回日期格式(yyyy-MM-dd HH:mm:ss)
//      System.out.println(getDateTime(new Date()));
//      //返回日期格式(yyyyMMdd)
//      System.out.println(getDateTime2(new Date()));
//      //获取当前年月格式(yyyyMM)
//      System.out.println(getCurrentMonth());
//      // 获得本月的几个月。(yyyyMM)
//      System.out.println(getBAMonth(-8));
//      //获取本月第一天日期（格式如YYYYMMDD）,如果当前日为当月1日,则返回上月第一日
//      System.out.println(getMonthFirstDay());
//      //获取当前时间前几天或后几天的日期
//      System.out.println(getAddDays(-1));
//      //获取某个月后的日期格式
//      System.out.println(getAfterMonth(-1));  
        System.out.println(getPredate());
//      System.out.println(getFristDayOfMonth());  
//      //  
//      System.out.println(getCurrentYear());  
//      //  
//      System.out.println(getMonth());  
//      //  
//      System.out.println(getDayOfMonth());  

    }

    /**
     * 计算传入时间和当前时间的差（格式**日**时**分）
     *
     * @param strtime
     * @return
     */
    public static String nowBetweenStrtime(String strtime) {
        long nd = 24 * 60 * 60;
        long nh = 60 * 60;
        long nm = 60;
        Date datenow = new Date();
        Date datestr = null;
        try {
            datestr = sdfDateTimeFormat.parse(strtime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long diff = (datenow.getTime() - datestr.getTime()) / 1000;
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";

    }

    /**
     * 比较两个yyyymmdd格式日期相差的天数
     *
     * @param startTime
     * @return
     */
    public static Integer nowBetweenStartTime(String startTime) {
        Integer data = 0;
        try {
            Date dataSta = g_SimpleDateFormat_I.parse(startTime);
            Date dataNow = g_SimpleDateFormat_I.parse(getCurDateTime());
            long a = (dataNow.getTime() - dataSta.getTime()) / 86400000;
            data = (int) a;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }


    /**
     * 获取两个日期相差的月数
     *
     * @param d1 较大的日期
     * @param d2 较小的日期
     * @return 如果d1>d2返回 月数差 否则返回0
     */
    public static int getMonthDiff(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        if (c1.getTimeInMillis() < c2.getTimeInMillis()) return 0;
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        // 获取年的差值 假设 d1 = 2015-8-16  d2 = 2011-9-30
        int yearInterval = year1 - year2;
        // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
        if (month1 < month2 || month1 == month2 && day1 < day2) yearInterval--;
        // 获取月数差值
        int monthInterval = (month1 + 12) - month2;
        if (day1 < day2) monthInterval--;
        monthInterval %= 12;
        return yearInterval * 12 + monthInterval;
    }

    public static String changeDate(String time) {
        String data = null;
        try {
            Date datan = sdfDateTimeFormat.parse(time);
            data = getDateTime_I(datan);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }

    //    获取传入日期所在月的第一天
    public static Date getFirstDayDateOfMonth(Date date) {

        final Calendar cal = Calendar.getInstance();

        cal.setTime(date);

        final int last = cal.getActualMinimum(Calendar.DAY_OF_MONTH);

        cal.set(Calendar.DAY_OF_MONTH, last);

        return cal.getTime();

    }

    //    获取传入日期所在月的最后一天
    public static Date getLastDayOfMonth(final Date date) {

        final Calendar cal = Calendar.getInstance();

        cal.setTime(date);

        final int last = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        cal.set(Calendar.DAY_OF_MONTH, last);

        return cal.getTime();

    }

    /**
     * 日期加减——月
     * @param
     * @throws
     * @throws ParseException
     */

    public static String monthModified(String datetime,Integer num) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            date = sdf.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(Calendar.MONTH, num);
        date = cl.getTime();
        return sdf.format(date);
    }

//    获取输入日期的上一个月最后一天
    public static String beforeMonthLastDay(String datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = sdf.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
            //设置为指定日期
            c.setTime(date);
            //指定日期月份减去一
            c.add(Calendar.MONTH, -1);
            //指定日期月份减去一后的 最大天数
            c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));
            //获取最终的时间
            Date lastDateOfPrevMonth = c.getTime();
            return sdf.format(lastDateOfPrevMonth);
    }

    //    获取输入日期的上一年最后一天
    public static String beforeYearLastDay(String datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = sdf.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        //设置为指定日期
        c.setTime(date);
        //指定日期年份减去一
        c.add(Calendar.YEAR,-1);
        //指定日期年份减去一后的 最大天数
        c.set(Calendar.DAY_OF_YEAR, c.getActualMaximum(Calendar.DAY_OF_YEAR));
        //获取最终的时间
        Date lastDateOfPrevYear = c.getTime();
        return sdf.format(lastDateOfPrevYear);
    }

    /**
     * 毫秒转时间串
     */
    public static String getDateStrbyms(long ms) {
        Date date = new Date(ms);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 根据时间类型来获得开始时间和结束时间这一段时间的范围
     *
     * @param startTime
     * @param endTime
     * @param quarterStart
     * @param quarterEnd
     * @param dateType
     * @return
     */
    public static String getDateRang(String startTime, String endTime, String quarterStart, String quarterEnd, String dateType) {
        String dateRang = "";
        if ("year".equals(dateType)) {// 年份
            int intEndTime = Integer.parseInt(endTime) - 1;
            dateRang = startTime + "年～" + String.valueOf(intEndTime) + "年";
        } else if ("quarter".equals(dateType)) {// 季度
            dateRang = startTime + "-" + quarterStart + "季度～" + endTime + "-" + quarterEnd + "季度";
        } else if ("month".equals(dateType)) {// 月份
            String[] m = new String[2];
            m = endTime.split("-");
            int year = Integer.parseInt(m[0]);
            int month = Integer.parseInt(m[1]);
            if (month <= 1) {
                year--;
                month = 12;
                dateRang = startTime + "～" + year + "-" + month;
            } else if (month >= 11) {
                month--;
                dateRang = startTime + "～" + year + "-" + month;
            } else {
                month--;
                dateRang = startTime + "～" + year + "-0" + month;
            }
        } else {// 日
            dateRang = startTime + "～" + endTime;
        }
        return dateRang;
    }


    /**
     * 将字符型的时间转为Date型的时间
     *
     * @param date
     * @return
     */
    public static Date parse(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将字符型的时间转为Date型的时间
     *
     * @param date
     * @return
     */
    public static Date parse(String date, String dateType) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd" + dateType);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将Date型时间转换为字符型时间
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ssss");
        return sdf.format(date);
    }


    /**
     * 格式化时间
     *
     * @param date
     * @return
     */
    public static String dateFormat(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (date != null) {
            return sdf.format(date);
        } else
            return "";
    }

    /**
     * 如果传入时间为空则将其设置为Strings.DEFAULT_DATE
     *
     * @param date
     * @return
     */
//	public static Date formatDefault(Date date) {
//		if (date == null || date.toString() == "") {
//			return DateUtil.parse(Strings.DEFAULT_DATE);
//		} else {
//			return date;
//		}
//	}

    /**
     * 获得现在时间
     *
     * @return
     */
    public static String nowTime() {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(today);
    }

    /**
     * 获得现在时间
     *
     * @return
     */
    public static String nowTimeHHmmss() {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(today);
    }

    /**
     * 获得现在时间
     *
     * @return
     */
    public static String today() {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(today);
    }

    public static String cToday() {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(today);
    }

    /**
     * 格式化时间格式 例：2012-12-12 12:12:12.0 转成2012-12-12 12:12:12
     *
     * @param date
     * @return
     */
    public static String dateFomat(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String changeMinute(String atime, Integer minute) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = DateUtil.parse(atime);
        String time_now = format.format(date);
        long time = date.getTime() + (minute * 60 * 1000);
        Date date1 = new Date(time);
        String time_before = format.format(date1);
        return time_before;
    }

    /**
     * *****************************计算缴费日期**************************************
     * ***
     */
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 自然月
     *
     * @param payType
     * @param chargeType
     * @param date1
     * @param count
     * @return
     * @throws ParseException
     */
    public static String general(int payType, int chargeType, String date1, int count) throws ParseException {
        String date = date1;
        if (payType == 1) {
            int year = Integer.parseInt(date.split("-")[0]);
            int month = Integer.parseInt(date.split("-")[1]);
            int day = Integer.parseInt(date.split("-")[2]);
            if (chargeType == 3) { // 按年
                year += count;
            } else if (chargeType == 1) { // 按月
                if (count + month > 12) {
                    year += (count + month) / 12;
                    month = (count + month) % 12;
                    // if (month == 0) {
                    // month = 1;
                    // }
                } else {
                    month += count;
                }
                if (day > 28) {
                    if (getDayByMonth(year + "-" + month) < day) {
                        day = getDayByMonth(year + "-" + month);
                    }
                }
            } else if (chargeType == 2) { // 按季度
                if (count * 3 + month >= 12) {
                    year += (count * 3 + month) / 12;
                    month = (count * 3 + month) % 12;
                } else {
                    month += count * 3;
                }
                if (day > 28) {
                    if (getDayByMonth(year + "-" + month) < day) {
                        day = getDayByMonth(year + "-" + month);
                    }
                }
            } else if (chargeType == 0) { // 按天
                Calendar c = Calendar.getInstance();
                c.setTime(sdf.parse(date));
                c.add(Calendar.DAY_OF_MONTH, count);
                return sdf.format(c.getTime());
            }
            return sdf.format(sdf.parse(year + "-" + month + "-" + day));
        } else if (payType == 2) {
            return thisMonthEnd(date, count);
        } else if (payType == 3) {
            return nextMonthEnd(date, count);
        }
        return "";
    }

    /**
     * 本月底
     *
     * @param date
     * @param count
     * @return
     * @throws ParseException
     */
    public static String thisMonthEnd(String date, int count) throws ParseException {
        Calendar calendar = new GregorianCalendar();  
		
		/*int year = Integer.parseInt(date.split("-")[0]);
		int month = Integer.parseInt(date.split("-")[1]);
		if (count + month >= 12) {
			year += (count + month) / 12;
			month = (count + month) % 12;
		} else {
			month += count;
		}
		return sdf.format(sdf.parse(year + "-" + month + "-01"));*/

        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return sdf.format(calendar.getTime());

    }

    /**
     * 下个月月底
     *
     * @param date
     * @param count
     * @return
     * @throws ParseException
     */
    public static String nextMonthEnd(String date, int count) throws ParseException {
        String date1 = thisMonthEnd(date, count);
        int year = Integer.parseInt(date1.split("-")[0]);
        int month = Integer.parseInt(date1.split("-")[1]);
        if (month == 12) {
            year += 1;
            month = 1;
        } else {
            month += 1;
        }
        return sdf.format(sdf.parse(year + "-" + month + "-01"));
    }

    /**
     * 获取当前月份天数
     *
     * @param date
     * @return
     */
    public static int getDayByMonth(String date) {
        int year = Integer.parseInt(date.split("-")[0]);
        int month = Integer.parseInt(date.split("-")[1]);
        int days[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        if (2 == month && 0 == (year % 4) && (0 != (year % 100) || 0 == (year % 400))) {
            days[1] = 29;
        }
        return Integer.valueOf(days[month - 1]);
    }

    /**
     * 返回当前月份
     * @param date
     * @return
     */
    public static int getCurMonth(String date){
        int year = Integer.parseInt(date.split("-")[0]);
        int month = Integer.parseInt(date.split("-")[1]);
        return month;
    }

    /**
     * 两个时间相差几个小时
     */
    public static int minutesBetween(String smdate, String bdate) {
        long inT = DateUtil.parse(smdate).getTime();
        long outT = DateUtil.parse(bdate).getTime();
        long ss = (outT - inT) / (1000); // 共计秒数
        int MM = (int) ss / 60; // 共计分钟数
        return MM;
    }

    public static String getHexDate(String date) {
        return date.replace("-", "");
    }

    /** 某个时间的多少天前是什么时候 */
    public static String dateDiff(String date, int day) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            // Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(date));
            calendar.add(Calendar.DAY_OF_MONTH, day);
            date = dateFormat(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /** 此时的多少天前是什么时候 */
    public static String dateDiff(int day) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        date = calendar.getTime();
        return dateFormat(date);
    }

    /**
     * 根据某个日期得到前一天日期
     *
     * @param d
     * @return
     */
    public static Date getBeforeDate(Date d) {
        Date date = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }

    /**
     * 得到某个日期的后一天日期
     *
     * @param d
     * @return
     */
    public static Date getAfterDate(Date d) {
        Date date = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        date = calendar.getTime();
        return date;
    }

    /**
     * 得到某个日期前后几天日期
     *
     * @param d
     * @return
     */
    public static Date getAfterDate(Date d, int i) {
        Date date = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.DAY_OF_MONTH, i);
        date = calendar.getTime();
        return date;
    }
    /**
     * 分钟差
     * @param date
     * @param i
     * @return
     */
    public static Date getBeforeDay_minute(Date date, int i){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.MINUTE, i);
        date = calendar.getTime();
        return date;
    }
    /**
     * 小时差
     * @param date
     * @param i
     * @return
     */
    public static String getBeforeDay_hour(Date date, int i)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.HOUR, i);
        date = calendar.getTime();
        return dateFormat(date);
    }


    public static long dateToLong(String date) {
        try {
            if (date.length() <= 10) {
                date = date + " 23:59:59";
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            return df.parse(date).getTime();
        } catch (Exception e) {
            //LogUtils.error("判断月卡是否过期时转换时间错误", e);
            return 10000000;
        }
    }

    public static String nowTimeMS() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss,SSS");
        String datetime = sdf.format(new Date());
        return datetime;
    }

    /**
     * 到某个时间还有多少毫秒
     *
     * @param
     * @return
     * @return
     * @return
     * @return
     * @return
     * @return
     * @return
     */
    public static int getTimeMS(String dstr) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dstr);
            long s1 = date.getTime();// 将时间转为毫秒
            long s2 = System.currentTimeMillis();// 得到当前的毫秒
            int day = (int) (s1 - s2);
            return day;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 是否是周末或周六
     *
     * @param date
     * @return true 是 else 否
     */
    public static boolean isSaturday(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)
            return true;
        else
            return false;
    }

} 