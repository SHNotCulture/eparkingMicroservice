package com.eparking.thread;

import com.common.entity.eparkingCloud.*;
import com.eparking.service.*;
import com.common.util.DateUtil;
import com.eparking.util.SpringUtils;
import com.eparking.writeLock.MonthlyRechargeLock;
import com.eparking.writeLock.ParkReportLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 21:402018-11-3
 * @Modified By:
 */
@Async
@Component
public class reportScheduled {
    private static final Logger logger = LoggerFactory.getLogger(reportScheduled.class);
    @Autowired
    private ParkReportService parkReportService;
    @Autowired
    private BusineService busineService;
    @Autowired
    private MonthlyRechargeLock monthlyRechargeLock;
    @Autowired
    private CarParkService carParkService;
    @Autowired
    private ParkReportLock parkReportLock;
    @Autowired
    private TCarWalletReportService tCarWalletReportService;
    @Autowired
    private TChargeDetailsReportService tChargeDetailsReportService;

    /**
     * 每日清晨一点触发
     */
    @Scheduled(cron = "0 0 1 * *  ?")
    public void scheduled(){
        long startTime;
        long E_time;
        startTime = System.currentTimeMillis();
        logger.info("开始财务日报表统计");
        logger.info("开始每日钱包支付报表统计");
        logger.info("开始每日分类报表统计");
/*        logger.info("开始每日电子对账统计");
        startTime = System.currentTimeMillis();
        parkReportService.paymentForDay();
        E_time = System.currentTimeMillis() - startTime;
        logger.info("执行 电子对账统计 耗时为：" + E_time + "ms");*/
        //设置查询条件
        parkReportLock = SpringUtils.getBean(ParkReportLock.class);
        carParkService = SpringUtils.getBean(CarParkService.class);
        parkReportService = SpringUtils.getBean(ParkReportService.class);
        tChargeDetailsReportService = SpringUtils.getBean(TChargeDetailsReportService.class);
        TCompanyPark tCompanyPark = new TCompanyPark();
        List<TCompanyPark> tCompanyParkList = carParkService.getCarPark(tCompanyPark);
        //存储redis
        for(TCompanyPark tCompanyParkOne:tCompanyParkList){
            parkReportLock.setTParkReport(tCompanyParkOne);
        }
        //读取redis
        List<TCompanyPark> tCompanyParkListResult = parkReportLock.getTParkReport();
        //执行统计动作
        if(tCompanyParkListResult.size()>0){
            for(TCompanyPark tCompanyParkOne:tCompanyParkListResult){
                String beginDate = DateUtil.getPredate();
                String endDate = DateUtil.getPredate();
                TParkReport tParkReport = new TParkReport();
                tParkReport.setParkId(tCompanyParkOne.getId());
                tParkReport.setCompanyId(tCompanyParkOne.getCompanyId());
                parkReportService.parkReportStatistics(beginDate,endDate,"0",tParkReport);
                TCarWalletReport tCarWalletReport = new TCarWalletReport();
                tCarWalletReport.setParkId(tCompanyParkOne.getId());
                tCarWalletReport.setCompanyId(tCompanyParkOne.getCompanyId());
                tCarWalletReportService.statisticsNow(beginDate,endDate,tCarWalletReport);
                TChargeDetailsReport tChargeDetailsReport = new TChargeDetailsReport();
                tChargeDetailsReport.setCompanyId(tCompanyParkOne.getCompanyId());
                tChargeDetailsReport.setParkId(tCompanyParkOne.getId());
                String beginTime = tCompanyParkOne.getStatisticsBeginTime();
                String endTime = tCompanyParkOne.getStatisticsEndTime();
                if(beginTime!=null && !beginTime.equals("")){
                    beginDate = beginDate + " " + beginTime;
                }else{
                    beginDate = beginDate +" 00:00:00";
                }
                if(endTime!=null && !endTime.equals("")){
                    endDate = endDate + " " + endTime;
                }else{
                    endDate = endDate +" 23:59:59";
                }
                tChargeDetailsReportService.chargeReportStatistics(tChargeDetailsReport,beginDate,endDate);
            }
        }
        E_time = System.currentTimeMillis() - startTime;
        logger.info("执行 财务日报表统计 耗时为：" + E_time + "ms");
        logger.info("执行 钱包支付报表统计 耗时为：" + E_time + "ms");
        logger.info("执行 分类统计报表 耗时为：" + E_time + "ms");
    }

    /**
     * 每月1号清晨两点触发
     */
    @Scheduled(cron = "0 0 2 1 * ?")
    public void monthlyRecharge(){
        long startTime;
        long E_time;
        logger.info("开始每月商户自动充值");
        logger.info("开始财务月报表统计");
        startTime = System.currentTimeMillis();
        //商户充值
        monthlyRechargeLock = SpringUtils.getBean(MonthlyRechargeLock.class);
        TBusine tBusine = new TBusine();
//        tBusine.setParkId(2013);
        tBusine.setIsAuto(1);
        busineService = SpringUtils.getBean(BusineService.class);
        List<TBusine> tBusineList = busineService.getBusine(tBusine);
        for(TBusine tBusineOne : tBusineList){
            monthlyRechargeLock.setTheBusineRechage(tBusineOne);
        }
        List<TBusine> tBusineListRecharge = monthlyRechargeLock.getTheBusineRechage();
        if(tBusineListRecharge.size()>0){
            for(TBusine tBusineOne:tBusineListRecharge){
                busineService.monthlyRecharge(tBusineOne);
            }
        }

        //月报表
        String beginDate = DateUtil.beforeMonthLastDay(DateUtil.getCurDateYMR()).substring(0,7)+"-01";
        String endDate = DateUtil.beforeMonthLastDay(DateUtil.getCurDateYMR());
        parkReportLock = SpringUtils.getBean(ParkReportLock.class);
        parkReportService = SpringUtils.getBean(ParkReportService.class);
        TCompanyPark tCompanyPark = new TCompanyPark();
        List<TCompanyPark> tCompanyParkList = carParkService.getCarPark(tCompanyPark);
        //存储redis
        for(TCompanyPark tCompanyParkOne:tCompanyParkList){
            parkReportLock.setTParkReport(tCompanyParkOne);
        }
        //读取redis
        List<TCompanyPark> tCompanyParkListResult = parkReportLock.getTParkReport();
        //执行统计动作
        if(tCompanyParkListResult.size()>0){
            for(TCompanyPark tCompanyParkOne:tCompanyParkListResult){
                TParkReport tParkReport = new TParkReport();
                tParkReport.setParkId(tCompanyParkOne.getId());
                tParkReport.setCompanyId(tCompanyParkOne.getCompanyId());
                parkReportService.parkReportStatistics(beginDate,endDate,"1",tParkReport);
            }
        }

        E_time = System.currentTimeMillis() - startTime;
        logger.info("执行 财务月报表统计 耗时为：" + E_time + "ms");
        logger.info("执行 每月商户自动充值 耗时为：" + E_time + "ms");
    }

    /**
     * 每年1月1号清晨三点触发
     */
    @Scheduled(cron = "0 0 3 1 1 ? ")
    public void yearlyStatistics(){
        long startTime;
        long E_time;
        logger.info("开始财务年报表统计");
        startTime = System.currentTimeMillis();

        //年报表
        String beginDate = DateUtil.beforeYearLastDay(DateUtil.getCurDateYMR()).substring(0,4)+"-01-01";
        String endDate = DateUtil.beforeYearLastDay(DateUtil.getCurDateYMR());
        parkReportLock = SpringUtils.getBean(ParkReportLock.class);
        parkReportService = SpringUtils.getBean(ParkReportService.class);
        TCompanyPark tCompanyPark = new TCompanyPark();
        List<TCompanyPark> tCompanyParkList = carParkService.getCarPark(tCompanyPark);
        //存储redis
        for(TCompanyPark tCompanyParkOne:tCompanyParkList){
            parkReportLock.setTParkReport(tCompanyParkOne);
        }
        //读取redis
        List<TCompanyPark> tCompanyParkListResult = parkReportLock.getTParkReport();
        //执行统计动作
        if(tCompanyParkListResult.size()>0){
            for(TCompanyPark tCompanyParkOne:tCompanyParkListResult){
                TParkReport tParkReport = new TParkReport();
                tParkReport.setParkId(tCompanyParkOne.getId());
                tParkReport.setCompanyId(tCompanyParkOne.getCompanyId());
                parkReportService.parkReportStatistics(beginDate,endDate,"2",tParkReport);
            }
        }

        E_time = System.currentTimeMillis() - startTime;
        logger.info("执行 财务年报表统计 耗时为：" + E_time + "ms");
    }

}
