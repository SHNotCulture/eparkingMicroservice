package com.eparking.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.*;
import com.eparking.insideService.CustomizeInsideService;
import com.eparking.insideService.TParkReportInsideService;
import com.eparking.service.*;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 22:122018-11-1
 * @Modified By:
 */
@Service
public class ParkReportServiceImpl implements ParkReportService {
    private static final Logger logger = LoggerFactory.getLogger(ParkReportServiceImpl.class);
    @Autowired
    private TParkReportInsideService tParkReportInsideService;
    @Autowired
    private CustomizeInsideService customizeInsideService;
    @Autowired
    private ParkingRecordService parkingRecordService;
    @Autowired
    private MonthlyCarService monthlyCarService;
    @Autowired
    private BusinePayService businePayService;
    @Autowired
    private CarPaymentService carPaymentService;
    @Autowired
    private ParkReportMonthService parkReportMonthService;
    @Autowired
    private ParkReportYearService parkReportYearService;

    /**
     *设置查询条件
     * @param beginTime
     * @param endTime
     * @param tParkReport
     * @return
     */
/*    private TParkReportCriteria setCriteria(String beginTime,String endTime,TParkReport tParkReport){
        //查询日报表信息
        TParkReportCriteria tParkReportCriteria= new TParkReportCriteria();
        if(tParkReport!=null){
            TParkReportCriteria.Criteria criteria=tParkReportCriteria.createCriteria();
            if(tParkReport.getParkId()!=null)
            {
                criteria.andParkIdEqualTo(tParkReport.getParkId());
            }
            if(tParkReport.getCompanyId()!=null){
                criteria.andCompanyIdEqualTo(tParkReport.getCompanyId());
            }
            if(beginTime!=null&&endTime!=null&&beginTime!=""&&endTime!="")
            {
                criteria.andCreateDateBetween(beginTime,endTime);
            }

        }
        return  tParkReportCriteria;
    }*/
    /**
     * 获取数据总量
     * @param beginTime
     * @param endTime
     * @param tParkReport
     * @return
     */
/*    private Integer getCount(String beginTime,String endTime,TParkReport tParkReport){
        Integer total =(int)tParkReportMapper.countByExample(setCriteria( beginTime, endTime, tParkReport));
        return total;
    }*/

    /**
     * 查询日报表信息(分页)
     *
     * @param beginTime
     * @param endTime
     * @param tParkReport
     * @param page
     * @param limit
     * @return
     */
    public PageBean<TParkReport> getParkReportbyPage(String beginTime, String endTime, TParkReport tParkReport, Integer page, Integer limit) {
/*        PageHelper.startPage(page, limit,"id desc");
        List<TParkReport> tParkReportList=getParkReport(beginTime, endTime, tParkReport);
        Integer countNums =getCount(beginTime, endTime, tParkReport);
        PageBean<TParkReport> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tParkReportList);
        return pageData;*/
        return tParkReportInsideService.getTParkReportbyPage(tParkReport, beginTime, endTime, page, limit);
    }

    /**
     * 查询日报表信息
     *
     * @return
     */
    public List<TParkReport> getParkReport(String beginTime, String endTime, TParkReport tParkReport) {
/*        List<TParkReport> tParkReportList=tParkReportMapper.selectByExample(setCriteria(beginTime, endTime, tParkReport));
        return tParkReportList;*/
        return tParkReportInsideService.getTParkReport(tParkReport, beginTime, endTime);
    }

    /**
     * 导出日报表信息
     *
     * @param beginTime
     * @param endTime
     * @param tParkReport
     * @param title
     * @param response
     */
    public void exportList(String beginTime, String endTime, TParkReport tParkReport, String title, HttpServletResponse response) {
        List<TParkReport> tParkReportList = getParkReport(beginTime, endTime, tParkReport);
        List<ExeclParkReport> execlParkReports = new ArrayList<>();
//        logger.info("tParkReportList:"+tParkReportList);
        //当查找记录返回无数据时初始化ExeclParkReport防止数组越界
        if (tParkReportList.size() <= 0) {
            ExeclParkReport execlParkReport = new ExeclParkReport();
            execlParkReports.add(execlParkReport);
        } else {
            for (TParkReport tParkReportNew : tParkReportList) {
                ExeclParkReport execlParkReport = new ExeclParkReport();
                try {
                    execlParkReport = (ExeclParkReport) BeanCopyUtil.CopyBeanToBean(tParkReportNew, execlParkReport);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                execlParkReports.add(execlParkReport);
            }
        }
        try {
            ExportExcelUtil.exportExcel(response, title, title, title, execlParkReports);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map getParkReportSum(String beginTime, String endTime, String tabelName) {
//        return customizeMapper.selectReportSum(SessionUtil.getParkId(),beginTime,endTime,tabelName);
        Map map = JsonUtil.json2Map(customizeInsideService.selectReportSum(SessionUtil.getParkId(), beginTime, endTime, tabelName));
        return map;
    }

    @Override
    public String parkReportStatistics(String beginTime, String endTime, String type,TParkReport tParkReport) {
        String msg = "报表统计成功";
        switch (type) {
            //日报表统计
            case "0":
                //接收组
                List<TParkInOut> tParkInOutList = new ArrayList<>();
                List<TParkCar> tParkCarList = new ArrayList<>();
                List<TCarPayment> tCarPaymentCashList = new ArrayList<>();
                List<TCarPayment> tCarPaymentOnlinecList = new ArrayList<>();
                List<TBusinePay> tBusinePayList = new ArrayList<>();

                //查询条件
                Integer day = null;
                String date = null;
                try {
                    day = StringUtil3.daysBetween(DateUtil.parse(beginTime, DateUtil.g_SimpleDateFormat_I), DateUtil.parse(endTime, DateUtil.g_SimpleDateFormat_I));
                    //天数day循环用<=
                    for (int i = 0; i <= day; i++) {
                        date = StringUtil3.dateModified(beginTime, i);
                        String date1 = date + " 00:00:00";
                        String date2 = date + " 23:59:59";
                        TBusinePay tBusinePaySel = new TBusinePay();
                        tBusinePaySel.setParkId(tParkReport.getParkId());
                        tBusinePaySel.setCompanyId(tParkReport.getCompanyId());
                        //现金交易明细查询条件
                        TCarPayment tCarPaymentCashSel = new TCarPayment();
                        tCarPaymentCashSel.setParkId(tParkReport.getParkId());
                        tCarPaymentCashSel.setCompanyId(tParkReport.getCompanyId());
                        tCarPaymentCashSel.setPayType((short) 1);
                        //电子交易明细查询条件
                        TCarPayment tCarPaymentOnlineSel = new TCarPayment();
                        tCarPaymentOnlineSel.setParkId(tParkReport.getParkId());
                        tCarPaymentOnlineSel.setCompanyId(tParkReport.getCompanyId());
                        tCarPaymentOnlineSel.setPayType((short) 2);

                        TParkInOut tParkInOutSel = new TParkInOut();
                        tParkInOutSel.setParkId(tParkReport.getParkId());
                        tParkInOutSel.setCompanyId(tParkReport.getCompanyId());
                        TParkCar tParkCarSel = new TParkCar();
                        tParkCarSel.setParkId(tParkReport.getParkId());
                        tParkCarSel.setCompanyId(tParkReport.getCompanyId());
                        tParkInOutList = parkingRecordService.getTParkInOutForStatistics(tParkInOutSel, date1, date2);
                        tParkCarList = monthlyCarService.getTParkCarForStatistics(tParkCarSel, date);
                        tBusinePayList = businePayService.getTBusinePay(tBusinePaySel, 0, date1, date2);
                        tCarPaymentCashList = carPaymentService.getCarPayment(tCarPaymentCashSel, date1, date2);
                        tCarPaymentOnlinecList = carPaymentService.getCarPayment(tCarPaymentOnlineSel, date1, date2);

                        //tParkReport各字段存储变量
                        Double tempNeedpayTotal = 0.0;
                        Double tempCashActualTotal = 0.0;
                        Double tempBehalfpayTotal = 0.0;
                        Double tempQpasspayTotal = 0.0;
                        Double tempPrepayTotal = 0.0;
                        Integer fixAdd = 0;
                        Double fixCashNeedTotal = 0.0;
                        Double fixCashActualTotal = 0.0;
                        Double fixOnlineNeedTotal = 0.0;
                        Double fixOnlineActualTotal = 0.0;
                        Double businessCircleTotal = 0.0;
                        Integer freeTotal = 0;
                        Double tempPayDiff = 0.0;

                        //各变量从返回list中赋值
                        for (TParkInOut tParkInOutResult : tParkInOutList) {
                            tempNeedpayTotal += tParkInOutResult.getNeedPay();
                            tempCashActualTotal += tParkInOutResult.getActualPay();
                            tempBehalfpayTotal += tParkInOutResult.getCoupon();
                            tempQpasspayTotal += tParkInOutResult.getQpassPay();
                            tempPrepayTotal += tParkInOutResult.getBeforePay();
                            tempPayDiff += tParkInOutResult.getNeedPay() - tParkInOutResult.getActualPay() - tParkInOutResult.getCoupon() - tParkInOutResult.getQpassPay() - tParkInOutResult.getBeforePay() - tParkInOutResult.getLocalcoupon();
                        }
                        fixAdd = tParkCarList.size();
                        if(tCarPaymentCashList.size()>0){
                            for (TCarPayment tCarPaymentCashResult : tCarPaymentCashList) {
//                                logger.info(JsonUtil.beanToJson(tCarPaymentCashResult));
                                if(tCarPaymentCashResult.getNeedPay()==null){
                                    tCarPaymentCashResult.setNeedPay(0.0);
                                }
                                if(tCarPaymentCashResult.getActualPay()==null){
                                    tCarPaymentCashResult.setActualPay(0.0);
                                }
                                fixCashNeedTotal += tCarPaymentCashResult.getNeedPay();
                                fixCashActualTotal += tCarPaymentCashResult.getActualPay();
                            }
                        }
                        if(tCarPaymentOnlinecList.size()>0){
                            for (TCarPayment tCarPaymentOnlineResult : tCarPaymentOnlinecList) {
                                if(tCarPaymentOnlineResult.getNeedPay()==null){
                                    tCarPaymentOnlineResult.setNeedPay(0.0);
                                }
                                if(tCarPaymentOnlineResult.getActualPay()==null){
                                    tCarPaymentOnlineResult.setActualPay(0.0);
                                }
                                fixOnlineNeedTotal += tCarPaymentOnlineResult.getNeedPay();
                                fixOnlineActualTotal += tCarPaymentOnlineResult.getActualPay();
                            }
                        }
                        if(tBusinePayList.size()>0){
                            for (TBusinePay tBusinePayResult : tBusinePayList) {
                                if(tBusinePayResult.getActualPay()==null){
                                    tBusinePayResult.setActualPay(0.0);
                                }
                                businessCircleTotal += tBusinePayResult.getActualPay();
                            }
                        }
                        freeTotal = tParkInOutList.size();

                        //对tParkReport插入,先删除当天的记录
                        TParkReport tParkReportSel = new TParkReport();
                        tParkReportSel.setParkId(tParkReport.getParkId());
                        tParkReportSel.setCompanyId(tParkReport.getCompanyId());
                        List<TParkReport> parkReportListResult = getParkReport(date, date, tParkReportSel);
                        if (parkReportListResult.size() > 0) {
                            for (TParkReport tParkReportDel : parkReportListResult) {
                                tParkReportInsideService.DeleteTParkReport(tParkReportDel);
                            }
                        }
                        //执行插入
                        TParkReport tParkReportUpdate = new TParkReport();
                        tParkReportUpdate.setParkId(tParkReport.getParkId());
                        tParkReportUpdate.setCompanyId(tParkReport.getCompanyId());
                        tParkReportUpdate.setCreateDate(date);
                        tParkReportUpdate.setTempNeedpayTotal(tempNeedpayTotal);
                        tParkReportUpdate.setTempCashActualTotal(tempCashActualTotal);
                        tParkReportUpdate.setTempBehalfpayTotal(tempBehalfpayTotal);
                        tParkReportUpdate.setTempQpasspayTotal(tempQpasspayTotal);
                        tParkReportUpdate.setTempPrepayTotal(tempPrepayTotal);
                        tParkReportUpdate.setFixAdd(fixAdd);
                        tParkReportUpdate.setFixCashNeedTotal(fixCashNeedTotal);
                        tParkReportUpdate.setFixCashActualTotal(fixCashActualTotal);
                        tParkReportUpdate.setFixOnlineNeedTotal(fixOnlineNeedTotal);
                        tParkReportUpdate.setFixOnlineActualTotal(fixOnlineActualTotal);
                        tParkReportUpdate.setBusinessCircleTotal(businessCircleTotal);
                        tParkReportUpdate.setFreeTotal(freeTotal);
                        tParkReportUpdate.setTempPayDiff(tempPayDiff);
                        tParkReportInsideService.UpdateTParkReport(tParkReportUpdate);
                    }
                } catch (ParseException e) {
                    msg = "报表统计失败";
                    e.printStackTrace();
                }
                break;
            case "1":
                //月报表统计
                try {
                    parkReportMonthService.parkReportStatistics(beginTime, endTime ,tParkReport);
                } catch (Exception e) {
                    msg = "报表统计失败";
                    e.printStackTrace();
                }
                break;
            case "2":
                //年报表统计
                try {
                parkReportYearService.parkReportStatistics(beginTime, endTime,tParkReport);
                } catch (Exception e) {
                    msg = "报表统计失败";
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }

        return msg;
    }

    /**
     * 每日从日报表取数据至电子对账表
     *
     * @return
     */
    public String paymentForDay() {
        String result = "";
//        Integer successNum=customizeMapper.paymentForDay();
        Integer successNum = customizeInsideService.paymentForDay();
        result = String.format("成功从日报表取出%s条数据", successNum);
//        logger.info(result);
        return result;
    }

    public static void main(String[] args) {
        String beginTime = "2020-02-06";
        String endTime = "2020-04-03";

        for (int i = 0; i < 3; i++) {
            String beginDate = DateUtil.monthModified(beginTime, i)+"-01";
            System.out.println(beginDate);
        }

    }
}
