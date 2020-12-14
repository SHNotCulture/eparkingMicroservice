package com.common.util;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Create By zhengChuQiao
 * On 2017-12-28 09:33
 */
public class MD5Util {
    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin) {
        String result = null;
        try {
            result = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            result = byteArrayToHexString(md.digest(result.getBytes("UTF-8")));
        } catch (Exception exception) {
        }
        return result;
    }
    public static String monthHOUR(Integer m){
        SimpleDateFormat sdf_hh = new SimpleDateFormat("yyyyMMddHH");
        String str = "";
        Date myDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(myDate);
        c.add(Calendar.HOUR, m);
        myDate = c.getTime();
        str = sdf_hh.format(myDate);
        return str;
    }
    //对key值进行加上时间的加密
    public static String doMd5(String key){
        String date1=monthHOUR(0);
        String key1=MD5Encode(key+date1);
        return key1;
    }
}
