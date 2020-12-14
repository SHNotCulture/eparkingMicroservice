package com.eparking.service.impl;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.ExeclParkReportMonth;
import com.common.entity.eparkingCloud.TParkReport;
import com.common.entity.eparkingCloud.TParkReportMonth;
import com.eparking.insideService.TParkReportMonthInsideService;
import com.eparking.service.ParkReportMonthService;
import com.eparking.service.ParkReportService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 18:262018-11-6
 * @Modified By:
 */
@Service
public class ParkReportMonthServiceImpl implements ParkReportMonthService {
    private static final Logger logger = LoggerFactory.getLogger(ParkReportMonthServiceImpl.class);
    /*    @Autowired
        private TParkReportMonthMapper tParkReportMonthMapper;*/
    @Autowired
    private TParkReportMonthInsideService tParkReportMonthInsideService;
    @Autowired
    private ParkReportService parkReportService;

    /**
     *设置查询条件
     * @param beginTime
     * @param endTime
     * @param tParkReportMonth
     * @return
     */
/*    private TParkReportMonthCriteria setCriteria(String beginTime, String endTime, TParkReportMonth tParkReportMonth){
        //查询月报表信息
        TParkReportMonthCriteria tParkReportMonthCriteria= new TParkReportMonthCriteria();
        if(tParkReportMonth!=null){
            TParkReportMonthCriteria.Criteria criteria=tParkReportMonthCriteria.createCriteria();
            if(tParkReportMonth.getParkId()!=null)
            {
                criteria.andParkIdEqualTo(tParkReportMonth.getParkId());
            }
            if(beginTime!=null&&endTime!=null&&beginTime!=""&&endTime!="")
            {
                criteria.andCreateDateBetween(beginTime,endTime);
            }

        }
        return  tParkReportMonthCriteria;
    }*/
    /**
     * 获取数据总量
     * @param beginTime
     * @param endTime
     * @param tParkReportMonth
     * @return
     */
/*    private Integer getCount(String beginTime,String endTime,TParkReportMonth tParkReportMonth){
        Integer total =(int)tParkReportMonthMapper.countByExample(setCriteria( beginTime, endTime, tParkReportMonth));
        return total;
    }*/

    /**
     * 查询月报表信息(分页)
     *
     * @param beginTime
     * @param endTime
     * @param tParkReportMonth
     * @param page
     * @param limit
     * @return
     */
    public PageBean<TParkReportMonth> getParkReportMonthbyPage(String beginTime, String endTime, TParkReportMonth tParkReportMonth, Integer page, Integer limit) {
/*        PageHelper.startPage(page, limit,"id desc");
        List<TParkReportMonth> tParkReportMonthList=getParkReportMonth(beginTime, endTime, tParkReportMonth);
        Integer countNums =getCount(beginTime, endTime, tParkReportMonth);
        PageBean<TParkReportMonth> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tParkReportMonthList);
        return pageData;*/
        return tParkReportMonthInsideService.getTParkReportMonthbyPage(tParkReportMonth, beginTime, endTime, page, limit);
    }

    /**
     * 查询月报表信息
     *
     * @return
     */
    public List<TParkReportMonth> getParkReportMonth(String beginTime, String endTime, TParkReportMonth tParkReportMonth) {
/*        List<TParkReportMonth> tParkReportMonthList=tParkReportMonthMapper.selectByExample(setCriteria(beginTime,endTime,tParkReportMonth));
        return tParkReportMonthList;*/
        return tParkReportMonthInsideService.getTParkReportMonth(tParkReportMonth, beginTime, endTime);
    }

    /**
     * 导出月报表信息
     *
     * @param beginTime
     * @param endTime
     * @param tParkReportMonth
     * @param title
     * @param response
     */
    public void exportList(String beginTime, String endTime, TParkReportMonth tParkReportMonth, String title, HttpServletResponse response) {
        List<TParkReportMonth> tParkReportMonthList = getParkReportMonth(beginTime, endTime, tParkReportMonth);
        List<ExeclParkReportMonth> execlParkReportMonths = new ArrayList<>();
//        logger.info("tParkReportMonthList:"+tParkReportMonthList);
        //当查找记录返回无数据时初始化ExeclParkReportMonth防止数组越界
        if (tParkReportMonthList.size() <= 0) {
            ExeclParkReportMonth execlParkReportMonth = new ExeclParkReportMonth();
            execlParkReportMonths.add(execlParkReportMonth);
        } else {
            for (TParkReportMonth tParkReportNew : tParkReportMonthList) {
                ExeclParkReportMonth execlParkReportMonth = new ExeclParkReportMonth();
                try {
                    execlParkReportMonth = (ExeclParkReportMonth) BeanCopyUtil.CopyBeanToBean(tParkReportNew, execlParkReportMonth);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                execlParkReportMonths.add(execlParkReportMonth);
            }
        }
        try {
            ExportExcelUtil.exportExcel(response, title, title, title, execlParkReportMonths);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String parkReportStatistics(String beginTime, String endTime, TParkReport tParkReport)throws Exception {
            Integer monthNum = DateUtil.getMonthDiff(DateUtil.parse(endTime, DateUtil.g_SimpleDateFormat_I), DateUtil.parse(beginTime, DateUtil.g_SimpleDateFormat_I)) + 1;
//            System.out.println(monthNum);
            //按月查询当月的财务汇总
            for (int i = 0; i < monthNum; i++) {
                //tParkReport各字段存储变量
                String createDate = "";
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

                //设置当月的查询条件
                TParkReport tParkReportSel = new TParkReport();
                tParkReportSel.setParkId(tParkReport.getParkId());
                tParkReportSel.setCompanyId(tParkReport.getCompanyId());
                String beginDate = DateUtil.monthModified(beginTime, i)+"-01";
//                System.out.println(beginDate);
                String firstDay = DateUtil.format(DateUtil.getFirstDayDateOfMonth(DateUtil.parse(beginDate, DateUtil.g_SimpleDateFormat_I)), DateUtil.g_SimpleDateFormat_I);
                String lastDay = DateUtil.format(DateUtil.getLastDayOfMonth(DateUtil.parse(beginDate, DateUtil.g_SimpleDateFormat_I)), DateUtil.g_SimpleDateFormat_I);
                List<TParkReport> tParkReportResultList = parkReportService.getParkReport(firstDay, lastDay, tParkReportSel);

                //汇总当月每一天
                if (tParkReportResultList.size() > 0) {
                    for (TParkReport tParkReportOne : tParkReportResultList) {
                        tempNeedpayTotal += tParkReportOne.getTempNeedpayTotal();
                        tempCashActualTotal += tParkReportOne.getTempCashActualTotal();
                        tempBehalfpayTotal += tParkReportOne.getTempBehalfpayTotal();
                        tempQpasspayTotal += tParkReportOne.getTempQpasspayTotal();
                        tempPrepayTotal += tParkReportOne.getTempPrepayTotal();
                        fixAdd += tParkReportOne.getFixAdd();
                        fixCashNeedTotal += tParkReportOne.getFixCashNeedTotal();
                        fixCashActualTotal += tParkReportOne.getFixCashActualTotal();
                        fixOnlineNeedTotal += tParkReportOne.getFixOnlineNeedTotal();
                        fixOnlineActualTotal += tParkReportOne.getFixOnlineActualTotal();
                        businessCircleTotal += tParkReportOne.getBusinessCircleTotal();
                        freeTotal += tParkReportOne.getFreeTotal();
                        tempPayDiff += tParkReportOne.getTempPayDiff();
                    }
                }

                //查询已存在当月的月报表记录进行删除
                TParkReportMonth tParkReportMonthSel = new TParkReportMonth();
                tParkReportMonthSel.setParkId(tParkReport.getParkId());
                List<TParkReportMonth> tParkReportMonthListResult = tParkReportMonthInsideService.getTParkReportMonth(tParkReportMonthSel,beginDate.substring(0,7),beginDate.substring(0,7));
                if (tParkReportMonthListResult.size() > 0) {
                    for (TParkReportMonth tParkReportMonthDel : tParkReportMonthListResult) {
                        tParkReportMonthInsideService.DeleteTParkReportMonth(tParkReportMonthDel);
                    }
                }

                //执行插入
                TParkReportMonth tParkReportMonthUpdate = new TParkReportMonth();
                tParkReportMonthUpdate.setParkId(tParkReport.getParkId());
                tParkReportMonthUpdate.setCreateDate(beginDate.substring(0,7));
                tParkReportMonthUpdate.setTempNeedpayTotal(tempNeedpayTotal);
                tParkReportMonthUpdate.setTempCashActualTotal(tempCashActualTotal);
                tParkReportMonthUpdate.setTempBehalfpayTotal(tempBehalfpayTotal);
                tParkReportMonthUpdate.setTempQpasspayTotal(tempQpasspayTotal);
                tParkReportMonthUpdate.setTempPrepayTotal(tempPrepayTotal);
                tParkReportMonthUpdate.setFixAdd(fixAdd);
                tParkReportMonthUpdate.setFixCashNeedTotal(fixCashNeedTotal);
                tParkReportMonthUpdate.setFixCashActualTotal(fixCashActualTotal);
                tParkReportMonthUpdate.setFixOnlineNeedTotal(fixOnlineNeedTotal);
                tParkReportMonthUpdate.setFixOnlineActualTotal(fixOnlineActualTotal);
                tParkReportMonthUpdate.setBusinessCircleTotal(businessCircleTotal);
                tParkReportMonthUpdate.setFreeTotal(freeTotal);
                tParkReportMonthUpdate.setTempPayDiff(tempPayDiff);
                tParkReportMonthInsideService.UpdateTParkReportMonth(tParkReportMonthUpdate);
            }


        return null;
    }
}
