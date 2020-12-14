package com.common.util;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 14:392018-9-13
 * @Modified By:
 */
public class Common {
    public static final String User = "User";//登录用户信息
    public static final String Company = "Company";//登录公司信息
    public static final String ParkId = "ParkId";//当前系统选择的车场ID
    public static final String Park = "Park";//登录系统选择的车场
    public static final String Code = "code";//验证码
    public static final String Today = "to_days(out_time) = to_days(now())";
    public static final String Yesterday = "TO_DAYS(NOW( )) - TO_DAYS(out_time) <= 1";
    public static final String Week = "DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(out_time)";
    public static final String ToMonth = "DATE_FORMAT(out_time, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' )";
    public static final String LastMonth = "PERIOD_DIFF( date_format( now( ) , '%Y%m' ) , date_format( out_time, '%Y%m' ) ) =1";
    public static final String ToYear = "YEAR(out_time)=YEAR(NOW())";
    //正式
    public static final String Serve_eparkingCloudData = "eparkingCloudData";
    public static final String Serve_eparkingMqttClient = "eparkingMqttClient";
    public static final String Feign_eparkingCloudData = "/eparkingCloudData";
    public static final String Feign_eparkingMqttClient ="/eparkingMqttClient";
    //本地
//    public static final String Feign_eparkingCloudData = "";
    public static String getSqlWhere(String sqlType){
        String sqlwhere=null;
        if (sqlType.equals("Today")){
            sqlwhere = Today;
        }if (sqlType.equals("Yesterday")){
            sqlwhere = Yesterday;
        }if (sqlType.equals("Week")){
            sqlwhere = Week;
        }if (sqlType.equals("ToMonth")){
            sqlwhere = ToMonth;
        }if (sqlType.equals("LastMonth")){
            sqlwhere = LastMonth;
        }if (sqlType.equals("ToYear")){
            sqlwhere = ToYear;
        }
        return sqlwhere;
    }
    public static byte[] int2Bytes( int value )
    {
        byte[] src = new byte[4];
        src[3] =  (byte) ((value>>24) & 0xFF);
        src[2] =  (byte) ((value>>16) & 0xFF);
        src[1] =  (byte) ((value>>8) & 0xFF);
        src[0] =  (byte) (value & 0xFF);
        return src;
    }

}
