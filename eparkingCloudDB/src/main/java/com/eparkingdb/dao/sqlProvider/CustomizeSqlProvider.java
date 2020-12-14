package com.eparkingdb.dao.sqlProvider;

import com.common.entity.eparkingCloud.*;
import com.common.util.*;

import java.util.Date;

/**
 * @ClassName CustomizeSqlProvider
 * @Author jin
 * @Date 2018/9/28 18:52
 **/
public class CustomizeSqlProvider {

    public String selectMonthlyNum(String parkId) {
        String sql = "select a.totalCarNo,a.parkingNo,b.*,c.*,d.* from \n" +
                "(select IFNULL(SUM(length(car_plate) + 1 - length(REPLACE (car_plate, ',', ''))),0) totalCarNo,IFNULL(SUM(parking_no),0) parkingNo from t_park_car where park_id=" + parkId + ") a, \n" +
                "(SELECT IFNULL(SUM(parking_no),0) privatePchargeReportStatisticsarkingNo from t_park_car where is_owner=1 and park_id=" + parkId + ") b,\n" +
                "(select IFNULL(SUM(parking_no),0) pauseParkingNo from t_park_car WHERE status=2 and park_id=" + parkId + ")c,\n" +
                "(select IFNULL(SUM(parking_no),0) expiredParkingNo from t_park_car WHERE status=3 and park_id=" + parkId + ")d";
        return sql.toString();
    }

    public String selectMainNum(String parkId) {
        String sql = "select a.monEle,b.monCash,c.temporaryEle,d.temporaryCash,e.yueEle,f.yueCash from\n" +
                "(SELECT IFNULL(SUM(actual_pay),0) monEle FROM t_park_in_out_" + parkId + " where car_nature=2 and charge_type=1 and to_days(out_time) = to_days(now()))a,\n" +
                "(SELECT IFNULL(SUM(actual_pay),0) monCash FROM t_park_in_out_" + parkId + " where car_nature=2 and charge_type=3 and to_days(out_time) = to_days(now()))b,\n" +
                "(SELECT IFNULL(SUM(actual_pay),0) temporaryEle FROM t_park_in_out_" + parkId + " where car_nature=3 and charge_type=1 and to_days(out_time) = to_days(now()))c,\n" +
                "(SELECT IFNULL(SUM(actual_pay),0) temporaryCash FROM t_park_in_out_" + parkId + " where car_nature=3 and charge_type=3 and to_days(out_time) = to_days(now()))d,\n" +
                "(SELECT IFNULL(SUM(actual_pay),0) yueEle FROM t_park_in_out_" + parkId + " where charge_type=1 and DATE_FORMAT(out_time, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' ))e,\n" +
                "(SELECT IFNULL(SUM(actual_pay),0) yueCash FROM t_park_in_out_" + parkId + " where charge_type=3 and DATE_FORMAT(out_time, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' ))f";
        return sql.toString();
    }

    /**
     * 电子报表
     *
     * @return
     */
    public String paymentForDay() {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into t_electron_Payment \n" +
                " (park_id,create_date,temp_online_total,\n" +
                "fix_online_actual_total,temp_qPassPay_Total,temp_PrePay_Total,weChat_PrePay_Total,\n" +
                "alipay_PrePay_Total,third_PrePay_Total,\n" +
                "monthly_weChat_Total,temp_unionPay_Total,temp_card_Total,\n" +
                "temp_rights_Total,temp_weChat_Total,temp_alipay_Total)\n" +
                "select park_id,create_date,temp_online_total,\n" +
                "fix_online_actual_total,temp_qPassPay_Total,temp_PrePay_Total,weChat_PrePay_Total,\n" +
                "alipay_PrePay_Total,third_PrePay_Total,\n" +
                "monthly_weChat_Total,temp_unionPay_Total,temp_card_Total,\n" +
                "temp_rights_Total,temp_weChat_Total,temp_alipay_Total\n" +
                "FROM t_park_report where create_date=left(DATE_SUB(curdate(),INTERVAL 1 DAY),10)");
        return sql.toString();
    }

    /*    public String parkTiggerForInOut(String parkId)
        {
            StringBuilder sql=new StringBuilder();
            sql.append("CREATE TABLE t_park_in_out_" +parkId+ " SELECT * FROM t_park_in_out WHERE 1=2;");
            return sql.toString();
        }
        public String parkTiggerForTask(String parkId)
        {
            StringBuilder sql=new StringBuilder();
            sql.append("CREATE TABLE t_task_" +parkId+ " SELECT * FROM t_task WHERE 1=2");
            return sql.toString();
        }*/
    public String parkTiggerForInOut1(String parkId) {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE t_park_in_out_" + parkId + " like t_park_in_out;");
        return sql.toString();
    }

    public String parkTiggerForInOut2(String parkId) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into t_park_in_out_" + parkId + " SELECT * FROM t_park_in_out WHERE 1=2;");
        return sql.toString();
    }

    public String parkTiggerForTask1(String parkId) {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE t_task_" + parkId + " like t_task;");
        return sql.toString();
    }

    public String parkTiggerForTask2(String parkId) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into t_task_" + parkId + " SELECT * FROM t_task WHERE 1=2;");
        return sql.toString();
    }

    public String selectHistogram(String parkId, String year, String type) {
        String sql = "select month(out_time) as month,sum(" + type + ")nums from t_park_in_out_" + parkId + " where year(out_time) = " + year + " and " + type + ">0 group by month (out_time)";
        return sql.toString();
    }

    public String selectCompanyPark(String parkId) {
        String sql = "select (total_parking_space-in_fixprivate_cars-in_fixlots_cars-in_tempcars)totalVacancySpace,park_vacancy_space parkVacancySpace,private_vacancy_space privateVacancySpace,(in_fixprivate_cars+in_tempcars+in_fixlots_cars)inTotle,in_fixprivate_cars inFixprivate,in_fixlots_cars inFixlots,in_tempcars inTemp,incar_count_today inCars,outcar_count_today outCars,noconfirmcar_count_today noconfirmCar  from t_company_park where id=" + parkId;
        return sql.toString();
    }

    public String updateCompanyPark(String parkId, String column, Integer msg) {
        String sql = "update t_company_park set " + column + "=" + msg + " where id=" + parkId;
        return sql.toString();
    }


    public String insetParktable(Integer parkId, Integer parkCarId, ParkCarTask parkCarTask, String computerIndex) {
        String data = JsonUtil.beanToJson(parkCarTask);
        String tabateName = "t_task_" + parkId;
        String sql = "INSERT INTO " + tabateName + " (park_car_id,task_type,data,pk,task_status,create_time)" +
                " value(" + parkCarId + ",'registercar_update','" + data + "','" + computerIndex + "','0','" + DateUtil.dateFormat(new Date()) + "')";
        return sql.toString();
    }

    public String insertBlackTask(Integer parkId, BlackListTask blackListTask, String pk) {
        String data = JsonUtil.beanToJson(blackListTask);
        String tabateName = "t_task_" + parkId;
        String sql = "INSERT INTO " + tabateName + " (task_type,data,pk,task_status,create_time)" +
                " value('black_update','" + data + "','" + pk + "','0','" + DateUtil.dateFormat(new Date()) + "')";
        return sql.toString();
    }

    public String findParkPortInId(String ids) {
        String sql = "select * from t_park_port as p where p.id in(" + ids + ")";
        return sql.toString();
    }

    public String selectCloseParkCar(String closeDays, String payRule, String parkid) {
        String sql1 = "";
        String sql2 = "";
        if (closeDays.length() > 0) {
            sql1 = "and DATEDIFF(end_date,begin_date)<=" + closeDays + "";
        }
        if (payRule.length() > 0) {
            sql2 = "and pay_rule=" + payRule + "";
        }
        String sql = "select * from t_park_car where park_id=" + parkid + " " + sql1 + " " + sql2 + "";
        return sql.toString();
    }

    public String selectCloseParkCarCount(String closeDays, String payRule, String parkid) {
        String sql1 = "";
        String sql2 = "";
        if (closeDays.length() > 0) {
            sql1 = "and DATEDIFF(end_date,begin_date)<=" + closeDays + "";
        }
        if (payRule.length() > 0) {
            sql2 = "and pay_rule=" + payRule + "";
        }
        String sql = "select COUNT(*) from t_park_car where park_id=" + parkid + " " + sql1 + " " + sql2 + "";
        return sql.toString();
    }

    public String Resetport(String portids, String ids) {
        String sql = "UPDATE t_park_car SET port_id='" + portids + "' where id in (" + ids + ")";
        return sql.toString();
    }

    public String getMaturityNo(String parkid) {
        String sql = "SELECT COUNT(*) from t_park_car where end_date<=LAST_DAY(now()) and status=1 and park_id=" + parkid + "";
        return sql.toString();
    }

    public String selectParkingRecordNum(String parkid) {
        String sql = "select needpay_total_today needPay,cashpay_total_today cashPay,prepay_total_today beforePay,behalfpay_total_today coupon,qpasspay_total_today polymerization,localcouponpay_total_today relief from t_company_park where id=" + parkid + "";
        return sql.toString();
    }

    /**
     * 查找今日收入
     *
     * @param parkid
     * @return
     */
    public String selectTodayPay(String parkid) {
        String sql = "select create_date,temp_NeedPay_Total from t_park_report where to_days(create_date) = to_days(now()) and park_id=" + parkid + "";
        return sql.toString();
    }

    /**
     * 查找免单车辆数
     *
     * @param parkid
     * @return
     */
    public String selectFreeTotal(String parkid) {
        String sql = "select free_total from t_park_report where to_days(create_date) = to_days(now()) and park_id=" + parkid + "";
        return sql.toString();
    }

    /**
     * 查找今日车流量
     *
     * @param parkid
     * @return
     */
    public String selectTotalNum(String parkid) {
        String sql = "SELECT a.a+b.b total FROM\n" +
                "(select COUNT(*)a from t_park_in_out_" + parkid + " where to_days(in_time) = to_days(now()))a,\n" +
                "(select COUNT(*)b from t_park_in_out_" + parkid + " where to_days(out_time) = to_days(now()))b";
        return sql.toString();
    }

    /**
     * 查找今日分类型缴费总和
     *
     * @param parkid
     * @return
     */
    public String selectPayByType(String parkid) {
        String sql = "select IFNULL(w.temp_NeedPay_Total,0)NeedPay,IFNULL(w.free_total,0)free,IFNULL(w.temp_Pay_diff,0)diff,IFNULL(w.temp_BehalfPay_Total,0)BehalfPay,IFNULL(w.temp_qPassPay_Total,0)qPassPay,IFNULL(w.temp_PrePay_Total,0)PrePay,IFNULL(w.temp_coupon_Total,0)coupon from\n" +
                "(select 0 a)q left JOIN\n" +
                "(select 0 a,temp_NeedPay_Total,free_total,temp_Pay_diff,temp_BehalfPay_Total,temp_qPassPay_Total,temp_PrePay_Total,temp_coupon_Total from t_park_report where to_days(create_date) = to_days(now()) and park_id=" + parkid + ")w ON q.a=w.a";
        return sql.toString();
    }

    /**
     * 查找近七日的车流量
     *
     * @param parkid
     * @return
     */
    public String selectSevenDays(String parkid) {
        String sql = "select RIGHT(e.date,4)date,(ifnull(e.innum,0)+ifnull(b.outnum,0))num from \n" +
                "(select * from \n" +
                "(select date_format(d.date,'%Y%m%d')date from (select @num:=@num+1,date_format(adddate(DATE_SUB(CURDATE(), INTERVAL 7 DAY), INTERVAL @num DAY),'%Y-%m-%d') as date\n" +
                "from t_dic_qpasspay_type,(select @num:=0) t where adddate(DATE_SUB(CURDATE(), INTERVAL 7 DAY), INTERVAL @num DAY) < date_format(curdate(),'%Y-%m-%d')\n" +
                "order by date)d)c\n" +
                "LEFT JOIN\n" +
                "(select t.intime,COUNT(*)innum from \n" +
                "(select date_format(in_time,'%Y%m%d')intime,date_format(out_time,'%Y%m%d')outtime from t_park_in_out_" + parkid + " where to_days(in_time) >= to_days(now())-6)t\n" +
                "where t.intime is not null\n" +
                "GROUP BY t.intime)a ON c.date=a.intime)e\n" +
                "LEFT JOIN\n" +
                "(select t.outtime,COUNT(*)outnum from \n" +
                "(select date_format(in_time,'%Y%m%d')intime,date_format(out_time,'%Y%m%d')outtime from t_park_in_out_" + parkid + " where to_days(in_time) >= to_days(now())-6)t\n" +
                "where t.outtime is not null\n" +
                "GROUP BY t.outtime)b on e.date=b.outtime ORDER BY e.date";
        return sql.toString();
    }

    public String selectePayType(String parkid) {
        String sql = "select a.wechat,b.alipay,c.unionPay,d.actualPay,e.etc,f.coupon from\n" +
                "(select \"q\"q,COUNT(*) wechat from t_park_in_out_" + parkid + " where ePayType=3 and to_days(out_time)= to_days(now()))a left join\n" +
                "(select \"q\"q,COUNT(*) alipay from t_park_in_out_" + parkid + " where ePayType=4 and to_days(out_time)= to_days(now()))b on a.q=b.q left join\n" +
                "(select \"q\"q,COUNT(*) unionPay from t_park_in_out_" + parkid + " where ePayType in (1,2,5,6)  and to_days(out_time)= to_days(now()))c on a.q=c.q left join\n" +
                "(select \"q\"q,COUNT(*) actualPay from t_park_in_out_" + parkid + " where actual_pay>0  and to_days(out_time)= to_days(now()))d on a.q=d.q left join\n" +
                "(select \"q\"q,COUNT(*) etc from t_park_in_out_" + parkid + " where ePayType=7  and to_days(out_time)= to_days(now()))e on a.q=e.q left join\n" +
                "(select \"q\"q,COUNT(*) coupon from t_park_in_out_" + parkid + " where localcoupon>0 and to_days(out_time)= to_days(now()))f on a.q=f.q ";
        return sql.toString();
    }

    public String selectCarNum(String parkid) {
        String sql = "select total_parking_space total,(total_parking_space-in_fixprivate_cars-in_fixlots_cars-in_tempcars)surplus from t_company_park where id=" + parkid + "";
        return sql.toString();
    }

    public String selectMainAllNum(String parkid) {
        String sql = "\n" +
                "select a.overdueNum,b.todayNum,b.CashPay,b.NeedPay,b.free,b.BehalfPay,b.PrePay,b.coupon,b.qPassPay,b.diff,b.fixNeed,b.fixOline,c.wechat wechatNums,c.alipay alipayNums,c.coupon couponNums,c.unionPay unionPayNums,c.actualPay actualPayNums,c.etc etcNums,d.total,d.surplus,e.turnover from\n" +
                "(select \"q\"q, COUNT(*)overdueNum from t_park_car where park_id=" + parkid + " and status=3 )a\n" +
                "left join\n" +
                "(select \"q\"q,(incar_count_today+outcar_count_today)todayNum ,localcouponcar_count_today free,needpay_total_today NeedPay,behalfpay_total_today BehalfPay,cashpay_total_today CashPay\n" +
                ",prepay_total_today PrePay,localcouponpay_total_today coupon,qpasspay_total_today qPassPay,(needpay_total_today-behalfpay_total_today-prepay_total_today-localcouponpay_total_today-qpasspay_total_today-cashpay_total_today)diff,monthly_cashpay_today fixNeed,monthly_qpasspay_today fixOline from t_company_park where id=" + parkid + ")b\n" +
                "on a.q=b.q left join\n" +
                "(select \"q\"q,a.wechat,b.alipay,c.unionPay,d.actualPay,e.etc,f.coupon from (select \"q\"q,COUNT(*) wechat from t_park_in_out_" + parkid + " where ePayType=3 and to_days(out_time)= to_days(now()))a left join\n" +
                "(select \"q\"q,COUNT(*) alipay from t_park_in_out_" + parkid + " where ePayType=4 and to_days(out_time)= to_days(now()))b on a.q=b.q left join\n" +
                "(select \"q\"q,COUNT(*) unionPay from t_park_in_out_" + parkid + " where ePayType in (1,2,5,6)  and to_days(out_time)= to_days(now()))c on a.q=c.q left join\n" +
                "(select \"q\"q,COUNT(*) actualPay from t_park_in_out_" + parkid + " where actual_pay>0  and to_days(out_time)= to_days(now()))d on a.q=d.q left join\n" +
                "(select \"q\"q,COUNT(*) etc from t_park_in_out_" + parkid + " where ePayType=7  and to_days(out_time)= to_days(now()))e on a.q=e.q left join\n" +
                "(select \"q\"q,COUNT(*) coupon from t_park_in_out_" + parkid + " where localcoupon>0 and to_days(out_time)= to_days(now()))f on a.q=f.q)c\n" +
                "on a.q=c.q left join\n" +
                "(select \"q\"q,total_parking_space total,(total_parking_space-in_fixprivate_cars-in_fixlots_cars-in_tempcars)surplus from t_company_park where id=" + parkid + ")d\n" +
                "on a.q=d.q left join\n" +
                "(select \"q\"q,ROUND((outcar_count_today-incar_count_today)/total_parking_space,4)turnover from t_company_park where id=" + parkid + ")e\n" +
                "on a.q=e.q";
        return sql.toString();
    }

    public String selectDetail(String parkId, String whereType) {
        String sql = "select * from t_park_in_out_" + parkId + " where " + whereType + "";
        return sql.toString();
    }

    public String selectDetailNum(String parkId, String whereType) {
        String sql = "select count(*) from t_park_in_out_" + parkId + " where " + whereType + "";
        return sql.toString();
    }

    public String selectPresentCar(String parkId, String andSql) {
        String sql = "SELECT * from t_park_in_out_" + parkId + " where id in ( SELECT c.id from(SELECT a.id from t_park_in_out_" + parkId + " a inner join (SELECT in_car_plate, MAX( in_time ) AS 'in_time' FROM t_park_in_out_" + parkId + " WHERE in_time is not null and in_car_plate != '未识别' AND out_time is null group by in_car_plate ) b ON b.in_car_plate = a.in_car_plate and b.in_time = a.in_time ) c )" + andSql;
        return sql.toString();
    }

    public String insertExcel(String tableName, String column, String insertSql) {
        String sql = "INSERT INTO " + tableName + "(" + column + ") VALUE " + insertSql + "";
        return sql.toString();
    }

    public String selectPresentCarByCarplate(String parkId, String carplate) {
        String sql = "SELECT * FROM t_park_in_out_" + parkId + "  WHERE (out_type = 0 or out_type = 9 or out_type = 11) \n" +
                "and in_car_plate = '" + carplate + "' and in_time = (SELECT MAX(in_time) FROM t_park_in_out_" + parkId + " WHERE in_car_plate = '" + carplate + "')";
        return sql.toString();
    }

    public String selectPresentCarLikeCarplate(Integer parkId, String carplate) {
        String sql = "";
        if (StringUtil3.hasHz(carplate)) {
            sql = "SELECT DISTINCT in_car_plate as in_car_plate FROM t_park_in_out_" + parkId + "  where in_car_plate like '" + carplate + "%' and car_nature <> " + CommonUtil.CAR_NAME_2 + " AND (out_type =0 or out_type = 9  or out_type = 11) limit 10";
        } else {
            sql = "SELECT DISTINCT in_car_plate as in_car_plate FROM t_park_in_out_" + parkId + "  where in_car_plate like '%" + carplate + "%' and car_nature <> " + CommonUtil.CAR_NAME_2 + " AND (out_type =0 or out_type = 9  or out_type = 11) limit 10";
        }
        return sql.toString();
    }

    public String getTBusineTicketExpireSoon(Integer busineId, Integer ticketId) {
        String sql = "select r.id FROM(\n" +
                "SELECT a.*,IFNULL(b.y,0)y,IFNULL(b.m,0)m,IFNULL(b.d,0)d from\n" +
                "(SELECT * from t_busine_ticket where ticket_id=" + ticketId + " and busine_id=" + busineId + " and ticket_num>0 and expiry_date>NOW()\n" +
                "UNION\n" +
                "SELECT * from t_busine_ticket where ticket_id=" + ticketId + " and busine_id=" + busineId + " and ticket_num>0 and expiry_date=0)a\n" +
                "LEFT JOIN\n" +
                "(select q.batch_code,q.y,w.m,e.d from\n" +
                "(SELECT batch_code,COUNT(1)y from t_ticket_coupon\n" +
                "where ticket_id=" + ticketId + " and busine_id=" + busineId + " and DATE_FORMAT(update_time,'%Y')=DATE_FORMAT(NOW(),'%Y')\n" +
                "GROUP BY batch_code)q\n" +
                "LEFT JOIN\n" +
                "(SELECT batch_code,COUNT(1)m from t_ticket_coupon\n" +
                "where ticket_id=" + ticketId + " and busine_id=" + busineId + " and DATE_FORMAT(update_time,'%m')=DATE_FORMAT(NOW(),'%m')\n" +
                "GROUP BY batch_code)w on q.batch_code=w.batch_code\n" +
                "LEFT JOIN\n" +
                "(SELECT batch_code,COUNT(1)d from t_ticket_coupon\n" +
                "where ticket_id=" + ticketId + " and busine_id=" + busineId + " and DATE_FORMAT(update_time,'%d')=DATE_FORMAT(NOW(),'%d')\n" +
                "GROUP BY batch_code)e\n" +
                "on  w.batch_code=e.batch_code)b on a.id=b.batch_code)r\n" +
                "where (CASE WHEN limit_day=0 THEN 1=1 ELSE limit_day>d END) and (CASE WHEN limit_month=0 THEN 1=1 ELSE limit_month>m END) and (CASE WHEN limit_year=0 THEN 1=1 ELSE limit_year>y END)\n" +
                "LIMIT 1";
        return sql.toString();
    }

    public String getTBusineTicketExpireSoonAPI(BusineCouponGetTicketInfo busineCouponGetTicketInfo) {
        String sql = "select r.id FROM(\n" +
                "SELECT a.*,IFNULL(b.y,0)y,IFNULL(b.m,0)m,IFNULL(b.d,0)d from\n" +
                "(SELECT * from t_busine_ticket where ticket_id=" + busineCouponGetTicketInfo.getTicketId() + " and busine_id=" + busineCouponGetTicketInfo.getBusineId() + " and ticket_num>0 and expiry_date>NOW()\n" +
                "UNION\n" +
                "SELECT * from t_busine_ticket where ticket_id=" + busineCouponGetTicketInfo.getTicketId() + " and busine_id=" + busineCouponGetTicketInfo.getBusineId() + " and ticket_num>0 and expiry_date=0)a\n" +
                "LEFT JOIN\n" +
                "(select q.batch_code,q.y,w.m,e.d from\n" +
                "(SELECT batch_code,COUNT(1)y from t_ticket_coupon\n" +
                "where ticket_id=" + busineCouponGetTicketInfo.getTicketId() + " and busine_id=" + busineCouponGetTicketInfo.getBusineId() + " and DATE_FORMAT(update_time,'%Y')=DATE_FORMAT(NOW(),'%Y')\n" +
                "GROUP BY batch_code)q\n" +
                "LEFT JOIN\n" +
                "(SELECT batch_code,COUNT(1)m from t_ticket_coupon\n" +
                "where ticket_id=" + busineCouponGetTicketInfo.getTicketId() + " and busine_id=" + busineCouponGetTicketInfo.getBusineId() + " and DATE_FORMAT(update_time,'%m')=DATE_FORMAT(NOW(),'%m')\n" +
                "GROUP BY batch_code)w on q.batch_code=w.batch_code\n" +
                "LEFT JOIN\n" +
                "(SELECT batch_code,COUNT(1)d from t_ticket_coupon\n" +
                "where ticket_id=" + busineCouponGetTicketInfo.getTicketId() + " and busine_id=" + busineCouponGetTicketInfo.getBusineId() + " and DATE_FORMAT(update_time,'%d')=DATE_FORMAT(NOW(),'%d')\n" +
                "GROUP BY batch_code)e\n" +
                "on  w.batch_code=e.batch_code)b on a.id=b.batch_code)r\n" +
                "where (CASE WHEN limit_day=0 THEN 1=1 ELSE limit_day>d END) and (CASE WHEN limit_month=0 THEN 1=1 ELSE limit_month>m END) and (CASE WHEN limit_year=0 THEN 1=1 ELSE limit_year>y END)\n" +
                "LIMIT 1";
        return sql.toString();
    }

    public String getCouponPayMonth(TBusinesCoupon tBusinesCoupon, String year) {
        String sql = "SELECT substring(t.create_date, 6, 2) as date,sum(t.coupon_pay) as value FROM t_busines_coupon t where left(t.create_date,4)='" + year + "' and t.company_id=" + tBusinesCoupon.getCompanyId() + " and t.park_id=" + tBusinesCoupon.getParkId() + " and t.busine_id=" + tBusinesCoupon.getBusineId() + " GROUP BY  substring(t.create_date, 6, 2)";
        return sql.toString();
    }

    public String getCouponPayDay(TBusinesCoupon tBusinesCoupon, String year, String month) {
        String sql = "SELECT substring(t.create_date, 9, 2) as date,sum(t.coupon_pay) as value FROM t_busines_coupon t where left(t.create_date,7)='" + year + "-" + month + "' and t.company_id=" + tBusinesCoupon.getCompanyId() + " and t.park_id=" + tBusinesCoupon.getParkId() + " and t.busine_id=" + tBusinesCoupon.getBusineId() + " GROUP BY  substring(t.create_date, 9, 2)";
        return sql.toString();
    }

    public String getCouponSumPay(TBusinesCoupon tBusinesCoupon) {
        String sql = "SELECT (SELECT COALESCE(SUM(a.need_pay),0) as sum_need_pay FROM t_busine_pay a WHERE a.company_id ='" + tBusinesCoupon.getCompanyId() + "' AND a.park_id ='" + tBusinesCoupon.getParkId() + "' AND a.busine_id ='" + tBusinesCoupon.getBusineId() + "' and a.remark='购买电子券')+(SELECT COALESCE(SUM(b.coupon_pay),0) as sum_coupon_pay FROM t_busines_coupon b WHERE b.company_id ='" + tBusinesCoupon.getCompanyId() + "' AND b.park_id ='" + tBusinesCoupon.getParkId() + "' AND b.busine_id ='" + tBusinesCoupon.getBusineId() + "')";
        return sql.toString();
    }

    public String getCouponNeedPay(TBusinesCoupon tBusinesCoupon) {
        String sql = "SELECT COALESCE(SUM(need_pay),0) as sum_need_pay FROM t_busine_pay WHERE company_id ='" + tBusinesCoupon.getCompanyId() + "' AND park_id ='" + tBusinesCoupon.getParkId() + "' AND busine_id ='" + tBusinesCoupon.getBusineId() + "' AND (REMARK IS NULL or remark <> '购买电子券')";
        return sql.toString();
    }

    public String getParkInOutPay1(Integer parkId, String date1, String date2) {
        String sql = "SELECT round(SUM(need_pay),1) as need_pay,round(SUM(actual_pay),1) as actual_pay,round(SUM(coupon),1) " +
                "as coupon,round(SUM(qpass_pay),1) as qpass_pay,round(SUM(before_pay),1) as before_pay, round(SUM(localcoupon),1)" +
                " as localcoupon  FROM t_park_in_out_" + parkId + " AS p where p.out_time <= '" + date2 + "' AND p.out_time >= '" + date1 + "' GROUP BY p.park_id";
        return sql.toString();
    }

    public String getParkInOutPay2(Integer parkId, String date1, String date2, Integer payType) {
        String sql = "SELECT SUM(need_pay) as need_pay,SUM(actual_pay) as actual_pay FROM t_car_payment AS p " +
                "where p.pay_time <= '" + date2 + "' AND p.pay_time >= '" + date1 + "' AND p.park_id = " + parkId + " GROUP BY " +
                "p.park_id,p.pay_type HAVING p.pay_type = " + payType + "";
        return sql.toString();
    }

    public String getParkInOutPay3(Integer parkId, String date1, String date2, Integer payType) {
        String sql = "SELECT SUM(need_pay) as need_pay,SUM(actual_pay) as actual_pay FROM t_car_payment AS p " +
                "where p.pay_time <= '" + date2 + "' AND p.pay_time >= '" + date1 + "' AND p.park_id = " + parkId + " GROUP BY " +
                "p.park_id,p.pay_type HAVING p.pay_type = " + payType + "";
        return sql.toString();
    }

    public String getParkInOutPay4(Integer parkId, String date1, String date2) {
        String sql = "select case prepay_type when 1 then IFNULL(SUM(before_pay),0) else 0 end  weChatPrePayTotal,\r\n" +
                "case prepay_type when 2 then IFNULL(SUM(before_pay),0) else 0 end  alipayPrePayTotal,\r\n" +
                "case prepay_type when 3 then IFNULL(SUM(before_pay),0) else 0 end  thirdPrePayTotal \r\n" +
                " from t_park_in_out_" + parkId + " \r\n" +
                "where out_time <= '" + date2 + "'  AND out_time >= '" + date1 + "' \r\n" +
                "GROUP BY prepay_type LIMIT 1,1";
        return sql.toString();
    }

    public String getParkInOutPay5(Integer parkId, String date1, String date2) {
        String sql = "select IFNULL(SUM(actual_pay) ,0) monthly_weChat_Total FROM t_car_payment " +
                " where pay_type=2 and park_id = " + parkId + " and pay_time <= '" + date2 + "' AND pay_time >= '" + date1 + "'";
        return sql.toString();
    }

    public String getParkInOutPay6(Integer parkId, String date1, String date2) {
        String sql = "select \r\n" +
                "	    		IFNULL(SUM(temp_rights_Total), 0)  AS temp_rights_Total,\r\n" +
                "	    		IFNULL(SUM(temp_card_Total), 0)  AS temp_card_Total, \r\n" +
                "	    		IFNULL(SUM(temp_weChat_Total), 0)  AS temp_weChat_Total, \r\n" +
                "	    		IFNULL(SUM(temp_alipay_Total), 0)  AS temp_alipay_Total, \r\n" +
                "	    		IFNULL(SUM(temp_unionPay_Total), 0)  AS temp_unionPay_Total \r\n" +
                "	    		 from (\r\n" +
                "	    		select occur_time as occur_time,\r\n" +
                "	    		case ePayType when 1 then IFNULL(SUM(qpass_pay),0) else 0 end  temp_rights_Total,\r\n" +
                "	    		case ePayType when 2 then IFNULL(SUM(qpass_pay),0) else 0 end  temp_card_Total,\r\n" +
                "	    		case ePayType when 3 then IFNULL(SUM(qpass_pay),0) else 0 end  temp_weChat_Total,\r\n" +
                "	    		case ePayType when 4 then IFNULL(SUM(qpass_pay),0) else 0 end  temp_alipay_Total,\r\n" +
                "	    		case ePayType when 5 then IFNULL(SUM(qpass_pay),0) else 0 end  temp_unionPay_Total\r\n" +
                "	    		 from t_park_in_out_" + parkId + "\r\n" +
                "	    		where out_time <= '" + date2 + "' AND out_time >= '" + date1 + "'\r\n" +
                "	    		GROUP BY ePayType) T ";
        return sql.toString();
    }

    public String getParkInOutPay7(Integer parkId, String date1, String date2) {
        String sql = "SELECT * FROM t_company_park where id = " + parkId;
        return sql.toString();
    }

    public String getParkNum(Integer parkId, String date) {
        String sql = "select count(id) from t_park_car where park_id = " + parkId + " and begin_date = '" + date + "'";
        return sql.toString();
    }

    public String getFreeTotal(Integer parkId, String date1, String date2) {
        String sql = "select count(id) from t_park_in_out_" + parkId + " where park_id = " + parkId + " and out_time <= '" + date2 + "' AND out_time >= '" + date1 + "'";
        return sql.toString();
    }

    public String getBusinePay(Integer parkId, String date1, String date2) {
        String sql = "SELECT SUM(actual_pay) as total_actual_pay FROM t_busine_pay AS p where p.pay_time <= '" + date2 + "' AND p.pay_time >= '" + date1 + "' GROUP BY p.park_id HAVING p.park_id = " + parkId + "";
        return sql.toString();
    }

    public String deleteReport(Integer parkId, String date) {
        String sql = "DELETE FROM t_park_report WHERE park_id=" + parkId + " and create_date='" + date + "'";
        return sql.toString();
    }

    public String selectReport(Integer parkId, String date) {
        String sql = "select * FROM t_park_report WHERE park_id=" + parkId + " and create_date='" + date + "'";
        return sql.toString();
    }

    public String selectReportSum(Integer parkId, String startDate, String endDate, String tabelName) {
        String sql = "SELECT SUM(temp_cash_actual_total) tempCashActualTotal,SUM(temp_online_total)tempOnlineTotal,SUM(temp_needpay_total)tempNeedpayTotal,SUM(temp_behalfpay_total)tempBehalfpayTotal,SUM(temp_qpasspay_total)tempQpasspayTotal,SUM(temp_prepay_total)tempPrepayTotal from " + tabelName + " where park_id=" + parkId + " and create_date BETWEEN '" + startDate + "' and '" + endDate + "'";
        return sql.toString();
    }

    public String getTParkCarByCarplate(Integer parkId, String carplate) {
        String sql = "select * from t_park_car where park_id = " + parkId + " and FIND_IN_SET('" + carplate + "',car_plate)";
        return sql.toString();
    }

    public String getTBlacklistByCarplate(Integer parkId, String carplate) {
        String sql = "select * from t_blacklist where park_id = " + parkId + " and FIND_IN_SET('" + carplate + "',car_plate)";
        return sql.toString();
    }
    public String insetCouponble(Integer parkId, Shop shop, String computerIndex){
        String data = JsonUtil.beanToJson(shop);
        String tabateName = "t_task_"+parkId;
        String sql = "INSERT INTO "+tabateName+" (park_car_id,task_type,data,pk,task_status,create_time) " +
                "value('0','coupon_update','"+data+"','"+computerIndex+"','0','"+ DateUtil.getCurDateTime()+"')";
        return sql.toString();
    }
    public String selectCouponble(Integer parkId){
        String tabateName = "t_task_"+parkId;
        String sql = "select task_id,park_car_id,task_type,data,pk,task_status,create_time from  "+tabateName+" where task_type='coupon_update'";
        return sql.toString();
    }
    public String DeleteCouponble(Integer parkId,String taskId){
        String tabateName = "t_task_"+parkId;
        String sql = "delete from  "+tabateName+" where task_id="+taskId;
        return sql.toString();
    }
    /*    public String chargeReportStatistics(Integer parkId,String beginDate,String endDate) {
            String sql = "SELECT park_id,company_id,car_nature_desc,count( * ) AS charge_amount,SUM( localcoupon ) AS coupon_total,SUM( qpass_pay ) AS qpasspay_total,SUM( actual_pay ) AS actualpay_total,sum( need_pay ) AS needpay_total,sum( coupon ) AS  behalfpay_total,sum( before_pay ) AS prepay_total,SUBSTR( occur_time, 1, 10 ) AS charge_date ,(SELECT sum( qpass_pay ) FROM t_park_in_out_"+parkId+" WHERE ePayType = 12 and out_time IS NOT NULL AND car_nature_desc IS NOT NULL AND SUBSTR( occur_time, 1, 10 ) >= '"+beginDate+"' AND SUBSTR( occur_time, 1, 10 ) <= '"+endDate+"' ) as walletpay_total,(SELECT sum( localcoupon ) FROM t_park_in_out_"+parkId+" WHERE localcoupon_name='支付成功不抬杆'" +
                    "and out_time IS NOT NULL AND car_nature_desc IS NOT NULL AND SUBSTR( occur_time, 1, 10 ) >= '"+beginDate+"' AND SUBSTR( occur_time, 1, 10 ) <= '"+endDate+"' ) as inaccesspaid_total FROM t_park_in_out_"+parkId+" WHERE" +
                    "ePayType <> 12 and localcoupon_name<>'支付成功不抬杆' and out_time IS NOT NULL AND car_nature_desc IS NOT NULL AND SUBSTR( occur_time, 1, 10 ) >= '"+beginDate+"' AND SUBSTR( occur_time, 1, 10 ) <= '"+endDate+"' " +
                    "GROUP BY car_nature_desc,SUBSTR( occur_time, 1, 10 )";
            return sql.toString();
        }*/
/*public String chargeReportStatistics(Integer parkId,String beginDate,String endDate) {
    String sql = "SELECT\n" +
            "\tpark_id,\n" +
            "\tcompany_id,\n" +
            "\tcar_nature_desc,\n" +
            "\tcount( * ) AS charge_amount,\n" +
            "\tSUM( localcoupon ) AS coupon_total,\n" +
            "\tSUM( qpass_pay ) AS qpasspay_total,\n" +
            "\tSUM( actual_pay ) AS actualpay_total,\n" +
            "\tsum( need_pay ) AS needpay_total,\n" +
            "\tsum( coupon ) AS behalfpay_total,\n" +
            "\tsum( before_pay ) AS prepay_total,\n" +
            "\tSUBSTR( occur_time, 1, 10 ) AS charge_date,\n" +
            "\t(\n" +
            "SELECT\n" +
            "\tsum( qpass_pay ) \n" +
            "FROM\n" +
            "\tt_park_in_out_"+parkId+" \n" +
            "WHERE\n" +
            "\tePayType = 12 \n" +
            "\tAND out_time IS NOT NULL \n" +
            "\tAND car_nature_desc IS NOT NULL \n" +
            "\tAND occur_time >= '"+beginDate+"' \n" +
            "\tAND occur_time <= '"+endDate+"' \n" +
            "\t) AS walletpay_total,\n" +
            "\t(\n" +
            "SELECT\n" +
            "\tsum( localcoupon ) \n" +
            "FROM\n" +
            "\tt_park_in_out_"+parkId+" \n" +
            "WHERE\n" +
            "\tlocalcoupon_name = '支付成功不抬杆' \n" +
            "\tAND out_time IS NOT NULL \n" +
            "\tAND car_nature_desc IS NOT NULL \n" +
            "\tAND occur_time >= '"+beginDate+"' \n" +
            "\tAND occur_time <= '"+endDate+"' \n" +
            "\t) AS inaccesspaid_total \n" +
            "FROM\n" +
            "\tt_park_in_out_"+parkId+" \n" +
            "WHERE\n" +
            "\tePayType <> 12 \n" +
            "\tAND localcoupon_name <> '支付成功不抬杆' \n" +
            "\tAND out_time IS NOT NULL \n" +
            "\tAND car_nature_desc IS NOT NULL \n" +
            "\tAND occur_time >= '"+beginDate+"' \n" +
            "\tAND occur_time <= '"+endDate+"' \n" +
            "GROUP BY\n" +
            "\tcar_nature_desc,\n" +
            "\tSUBSTR( occur_time, 1, 10 )";
    return sql.toString();
}*/
    public String chargeReportStatistics(Integer parkId, String beginDate, String endDate) {
        String sql = "SELECT\n" +
                "\ta.park_id,\n" +
                "\ta.company_id,\n" +
                "\ta.car_nature_desc,\n" +
                "\tcount( * ) AS charge_amount,\n" +
                "\tSUM( a.localcoupon ) AS coupon_total,\n" +
                "\tSUM( a.qpass_pay ) AS qpasspay_total,\n" +
                "\tSUM( a.actual_pay ) AS actualpay_total,\n" +
                "\tSUM( a.need_pay ) AS needpay_total,\n" +
                "\tSUM( a.coupon ) AS behalfpay_total,\n" +
                "\tSUM( a.before_pay ) AS prepay_total,\n" +
                "\tb.walletpay_total,\n" +
                "\tc.inaccesspaid_total,\n" +
                "\tSUBSTR( a.occur_time, 1, 10 ) AS charge_date \n" +
                "FROM\n" +
                "\tt_park_in_out_" + parkId + " a\n" +
                "\tLEFT JOIN (\n" +
                "SELECT\n" +
                "\tcar_nature_desc,\n" +
                "\tsum( qpass_pay ) AS walletpay_total,\n" +
                "\tSUBSTR( occur_time, 1, 10 ) AS occur_time_b \n" +
                "FROM\n" +
                "\tt_park_in_out_" + parkId + " \n" +
                "WHERE\n" +
                "\tePayType = 12 \n" +
                "\tAND out_time IS NOT NULL \n" +
                "\tAND car_nature_desc IS NOT NULL \n" +
                "\tAND occur_time >= '" + beginDate + "' \n" +
                "\tAND occur_time <= '" + endDate + "' \n" +
                " GROUP BY\n" +
                "\tcar_nature_desc,\n" +
                "\tSUBSTR(occur_time, 1, 10 )" +
                "\t) b ON a.car_nature_desc = b.car_nature_desc \n" +
                "\tAND SUBSTR( a.occur_time, 1, 10 ) = b.occur_time_b\n" +
                "\tLEFT JOIN (\n" +
                "SELECT\n" +
                "\tcar_nature_desc,\n" +
                "\tsum( localcoupon ) AS inaccesspaid_total,\n" +
                "\tSUBSTR( occur_time, 1, 10 ) AS occur_time_c \n" +
                "FROM\n" +
                "\tt_park_in_out_" + parkId + " \n" +
                "WHERE\n" +
                "\tlocalcoupon_name = '支付成功不开杆' \n" +
                "\tAND out_time IS NOT NULL \n" +
                "\tAND car_nature_desc IS NOT NULL \n" +
                "\tAND occur_time >= '" + beginDate + "' \n" +
                "\tAND occur_time <= '" + endDate + "' \n" +
                " GROUP BY\n" +
                "\tcar_nature_desc,\n" +
                "\tSUBSTR(occur_time, 1, 10 )" +
                "\t) c ON a.car_nature_desc = c.car_nature_desc \n" +
                "\tAND SUBSTR( a.occur_time, 1, 10 ) = c.occur_time_c \n" +
                "WHERE\n" +
                "\ta.out_time IS NOT NULL \n" +
                "\tAND a.car_nature_desc IS NOT NULL \n" +
                "\tAND a.occur_time >= '" + beginDate + "' \n" +
                "\tAND a.occur_time <= '" + endDate + "' \n" +
                "GROUP BY\n" +
                "\ta.car_nature_desc,\n" +
                "\tSUBSTR( a.occur_time, 1, 10 )";
        return sql.toString();
    }

    public String getDutyStatistics(Integer parkId, String dutyPerson, String beginTime, String endTime) {
        String sqlDutyPerson = "";
        String sqlDutyPersonA = "";
        if (dutyPerson != null && !dutyPerson.equals("")) {
            sqlDutyPerson = "and duty_person='" + dutyPerson + "'";
            sqlDutyPersonA = "and a.duty_person='" + dutyPerson + "'";
        }

        String sql = "SELECT\n" +
                "\ta.park_id,\n" +
                "\ta.company_id,\n" +
                "\ta.duty_person,\n" +
                "\tcount( * ) AS charge_amount,\n" +
                "\tSUM( a.localcoupon ) AS coupon_total,\n" +
                "\tSUM( a.qpass_pay ) AS qpasspay_total,\n" +
                "\tSUM( a.actual_pay ) AS actualpay_total,\n" +
                "\tSUM( a.need_pay ) AS needpay_total,\n" +
                "\tSUM( a.coupon ) AS behalfpay_total,\n" +
                "\tSUM( a.before_pay ) AS prepay_total,\n" +
                "\tb.walletpay_total,\n" +
                "\tc.inaccesspaid_total,\n" +
                "\tSUBSTR( a.occur_time, 1, 10 ) AS charge_date \n" +
                "FROM\n" +
                "\tt_park_in_out_" + parkId + " a\n" +
                "\tLEFT JOIN (\n" +
                "SELECT\n" +
                "\tduty_person,\n" +
                "\tsum( qpass_pay ) AS walletpay_total,\n" +
                "\tSUBSTR( occur_time, 1, 10 ) AS occur_time_b \n" +
                "FROM\n" +
                "\tt_park_in_out_" + parkId + " \n" +
                "WHERE\n" +
                "\tePayType = 12 \n" +
                "\tAND out_time IS NOT NULL \n" +
                "\tAND duty_person IS NOT NULL \n" +
                "\tAND occur_time >= '" + beginTime + "' \n" +
                "\tAND occur_time <= '" + endTime + "' \n" +
                sqlDutyPerson +
                "\t GROUP BY\n" +
                "\tduty_person,\n" +
                "\tSUBSTR(occur_time, 1, 10 )" +
                "\t) b ON a.duty_person = b.duty_person \n" +
                "\tAND SUBSTR( a.occur_time, 1, 10 ) = b.occur_time_b\n" +
                "\tLEFT JOIN (\n" +
                "SELECT\n" +
                "\tduty_person,\n" +
                "\tsum( localcoupon ) AS inaccesspaid_total,\n" +
                "\tSUBSTR( occur_time, 1, 10 ) AS occur_time_c \n" +
                "FROM\n" +
                "\tt_park_in_out_" + parkId + " \n" +
                "WHERE\n" +
                "\tlocalcoupon_name = '支付成功不开杆' \n" +
                "\tAND out_time IS NOT NULL \n" +
                "\tAND duty_person IS NOT NULL \n" +
                "\tAND occur_time >= '" + beginTime + "' \n" +
                "\tAND occur_time <= '" + endTime + "' \n" +
                sqlDutyPerson +
                "\t GROUP BY\n" +
                "\tduty_person,\n" +
                "\tSUBSTR(occur_time, 1, 10 )" +
                "\t) c ON a.duty_person = c.duty_person \n" +
                "\tAND SUBSTR( a.occur_time, 1, 10 ) = c.occur_time_c \n" +
                "WHERE\n" +
                "\ta.out_time IS NOT NULL \n" +
                "\tAND a.duty_person IS NOT NULL \n" +
                "\tAND a.occur_time >= '" + beginTime + "' \n" +
                "\tAND a.occur_time <= '" + endTime + "' \n" +
                sqlDutyPersonA +
                "\tGROUP BY\n" +
                "\ta.duty_person,\n" +
                "\tSUBSTR( a.occur_time, 1, 10 )";
        return sql.toString();
    }

    public String getDutyStatisticsNum(Integer parkId, String dutyPerson, String beginTime, String endTime) {
        String sqlDutyPerson = "";
        String sqlDutyPersonA = "";
        if (dutyPerson != null && !dutyPerson.equals("")) {
            sqlDutyPerson = "and duty_person='" + dutyPerson + "'";
            sqlDutyPersonA = "and a.duty_person='" + dutyPerson + "'";
        }

        String sql = "SELECT count( * ) FROM (\n" +
                "\tSELECT\n" +
                "\tcount(*)\n" +
                "FROM\n" +
                "\tt_park_in_out_" + parkId + " a\n" +
                "\tLEFT JOIN (\n" +
                "SELECT\n" +
                "\tduty_person,\n" +
                "\tsum( qpass_pay ) AS walletpay_total,\n" +
                "\tSUBSTR( occur_time, 1, 10 ) AS occur_time_b \n" +
                "FROM\n" +
                "\tt_park_in_out_" + parkId + " \n" +
                "WHERE\n" +
                "\tePayType = 12 \n" +
                "\tAND out_time IS NOT NULL \n" +
                "\tAND duty_person IS NOT NULL \n" +
                "\tAND occur_time >= '" + beginTime + "' \n" +
                "\tAND occur_time <= '" + endTime + "' \n" +
                sqlDutyPerson +
                "\t GROUP BY\n" +
                "\tduty_person,\n" +
                "\tSUBSTR(occur_time, 1, 10 )" +
                "\t) b ON a.duty_person = b.duty_person \n" +
                "\tAND SUBSTR( a.occur_time, 1, 10 ) = b.occur_time_b\n" +
                "\tLEFT JOIN (\n" +
                "SELECT\n" +
                "\tduty_person,\n" +
                "\tsum( localcoupon ) AS inaccesspaid_total,\n" +
                "\tSUBSTR( occur_time, 1, 10 ) AS occur_time_c \n" +
                "FROM\n" +
                "\tt_park_in_out_" + parkId + " \n" +
                "WHERE\n" +
                "\tlocalcoupon_name = '支付成功不开杆' \n" +
                "\tAND out_time IS NOT NULL \n" +
                "\tAND duty_person IS NOT NULL \n" +
                "\tAND occur_time >= '" + beginTime + "' \n" +
                "\tAND occur_time <= '" + endTime + "' \n" +
                sqlDutyPerson +
                "\t GROUP BY\n" +
                "\tduty_person,\n" +
                "\tSUBSTR(occur_time, 1, 10 )" +
                "\t) c ON a.duty_person = c.duty_person \n" +
                "\tAND SUBSTR( a.occur_time, 1, 10 ) = c.occur_time_c \n" +
                "WHERE\n" +
                "\ta.out_time IS NOT NULL \n" +
                "\tAND a.duty_person IS NOT NULL \n" +
                "\tAND a.occur_time >= '" + beginTime + "' \n" +
                "\tAND a.occur_time <= '" + endTime + "' \n" +
                sqlDutyPersonA +
                "\tGROUP BY\n" +
                "\ta.duty_person,\n" +
                "\tSUBSTR( a.occur_time, 1, 10 )" +
                "\t) d";
        return sql.toString();
    }
}
