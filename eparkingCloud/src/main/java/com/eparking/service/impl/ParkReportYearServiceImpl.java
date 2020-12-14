package com.eparking.service.impl;

import com.common.entity.eparkingCloud.ExeclParkReportYear;
import com.common.entity.eparkingCloud.TParkReport;
import com.common.entity.eparkingCloud.TParkReportMonth;
import com.common.entity.eparkingCloud.TParkReportYear;
import com.eparking.insideService.TParkReportMonthInsideService;
import com.eparking.insideService.TParkReportYearInsideService;
import com.eparking.service.ParkReportYearService;
import com.common.util.BeanCopyUtil;
import com.common.util.ExportExcelUtil;
import com.common.entity.PageBean;
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
 * @Date Create in 18:322018-11-6
 * @Modified By:
 */
@Service
public class ParkReportYearServiceImpl implements ParkReportYearService {
    private static final Logger logger = LoggerFactory.getLogger(ParkReportYearServiceImpl.class);
    @Autowired
    private TParkReportYearInsideService tParkReportYearInsideService;
    @Autowired
    private TParkReportMonthInsideService tParkReportMonthInsideService;
    /**
     *设置查询条件
     * @param beginTime
     * @param endTime
     * @param tParkReportYear
     * @return
     */
/*    private TParkReportYearCriteria setCriteria(String beginTime, String endTime, TParkReportYear tParkReportYear){
        //查询全部车场信息
        TParkReportYearCriteria tParkReportYearCriteria= new TParkReportYearCriteria();
        if(tParkReportYear!=null){
            TParkReportYearCriteria.Criteria criteria=tParkReportYearCriteria.createCriteria();
            if(tParkReportYear.getParkId()!=null)
            {
                criteria.andParkIdEqualTo(tParkReportYear.getParkId());
            }
            if(beginTime!=null&&endTime!=null &&beginTime!=""&&endTime!="")
            {
                criteria.andCreateDateBetween(beginTime,endTime);
            }

        }
        return  tParkReportYearCriteria;
    }*/
    /**
     * 获取数据总量
     * @param beginTime
     * @param endTime
     * @param tParkReportYear
     * @return
     */
/*    private Integer getCount(String beginTime,String endTime,TParkReportYear tParkReportYear){
        Integer total =(int)tParkReportYearMapper.countByExample(setCriteria( beginTime, endTime, tParkReportYear));
        return total;
    }*/

    /**
     * 查询年报表信息(分页)
     *
     * @param beginTime
     * @param endTime
     * @param tParkReportYear
     * @param page
     * @param limit
     * @return
     */
    public PageBean<TParkReportYear> getParkReportYearbyPage(String beginTime, String endTime, TParkReportYear tParkReportYear, Integer page, Integer limit) {
/*        PageHelper.startPage(page, limit,"id desc");
        List<TParkReportYear> tParkReportYears=getParkReportYear(beginTime, endTime, tParkReportYear);
        Integer countNums =getCount(beginTime, endTime, tParkReportYear);
        PageBean<TParkReportYear> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tParkReportYears);
        return pageData;*/
        return tParkReportYearInsideService.getTParkReportYearbyPage(tParkReportYear, beginTime, endTime, page, limit);
    }

    /**
     * 查询年报表信息
     *
     * @return
     */
    public List<TParkReportYear> getParkReportYear(String beginTime, String endTime, TParkReportYear tParkReportYear) {
/*        List<TParkReportYear> tParkReportYearList=tParkReportYearMapper.selectByExample(setCriteria( beginTime, endTime, tParkReportYear));
        return tParkReportYearList;*/
        return tParkReportYearInsideService.getTParkReportYear(tParkReportYear, beginTime, endTime);
    }

    /**
     * 导出日报表信息
     *
     * @param beginTime
     * @param endTime
     * @param tParkReportYear
     * @param title
     * @param response
     */
    public void exportList(String beginTime, String endTime, TParkReportYear tParkReportYear, String title, HttpServletResponse response) {
        List<TParkReportYear> tParkReportYears = getParkReportYear(beginTime, endTime, tParkReportYear);
        List<ExeclParkReportYear> execlParkReportYears = new ArrayList<>();
//        logger.info("tParkReportYears:"+tParkReportYears);
        //当查找记录返回无数据时初始化ExeclParkReportYear防止数组越界
        if (tParkReportYears.size() <= 0) {
            ExeclParkReportYear execlParkReportYear = new ExeclParkReportYear();
            execlParkReportYears.add(execlParkReportYear);
        } else {
            for (TParkReportYear tParkReportNew : tParkReportYears) {
                ExeclParkReportYear execlParkReport = new ExeclParkReportYear();
                try {
                    execlParkReport = (ExeclParkReportYear) BeanCopyUtil.CopyBeanToBean(tParkReportNew, execlParkReport);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                execlParkReportYears.add(execlParkReport);
            }
        }
        try {
            ExportExcelUtil.exportExcel(response, title, title, title, execlParkReportYears);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String parkReportStatistics(String beginTime, String endTime, TParkReport tParkReport) throws Exception {

            String beginYear = beginTime.substring(0,beginTime.indexOf("-"));
            String endYear = endTime.substring(0,endTime.indexOf("-"));
            Integer yearNum = Integer.valueOf(endYear)-Integer.valueOf(beginYear)+1;


            //按年数查询当年的财务汇总
            for (int i = 0; i < yearNum; i++) {
                //tParkReportMonth各字段存储变量
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
                //设置当年的查询条件
                TParkReportMonth tParkReportMonthSel = new TParkReportMonth();
                tParkReportMonthSel.setParkId(tParkReport.getParkId());
                String beginMonth = beginYear+"-01";
                String endMonth = beginYear+"-12";
                List<TParkReportMonth> tParkReportMonthResultList = tParkReportMonthInsideService.getTParkReportMonth(tParkReportMonthSel,beginMonth,endMonth);

                //汇总当年每一月
                if (tParkReportMonthResultList.size() > 0) {
                    for (TParkReportMonth tParkReportMonthOne : tParkReportMonthResultList) {
                        tempNeedpayTotal += tParkReportMonthOne.getTempNeedpayTotal();
                        tempCashActualTotal += tParkReportMonthOne.getTempCashActualTotal();
                        tempBehalfpayTotal += tParkReportMonthOne.getTempBehalfpayTotal();
                        tempQpasspayTotal += tParkReportMonthOne.getTempQpasspayTotal();
                        tempPrepayTotal += tParkReportMonthOne.getTempPrepayTotal();
                        fixAdd += tParkReportMonthOne.getFixAdd();
                        fixCashNeedTotal += tParkReportMonthOne.getFixCashNeedTotal();
                        fixCashActualTotal += tParkReportMonthOne.getFixCashActualTotal();
                        fixOnlineNeedTotal += tParkReportMonthOne.getFixOnlineNeedTotal();
                        fixOnlineActualTotal += tParkReportMonthOne.getFixOnlineActualTotal();
                        businessCircleTotal += tParkReportMonthOne.getBusinessCircleTotal();
                        freeTotal += tParkReportMonthOne.getFreeTotal();
                        tempPayDiff += tParkReportMonthOne.getTempPayDiff();
                    }
                }

                //查询已存在当年的月报表记录进行删除
                TParkReportYear tParkReportYearSel = new TParkReportYear();
                tParkReportYearSel.setParkId(tParkReport.getParkId());
                List<TParkReportYear> tParkReportYearListResult = tParkReportYearInsideService.getTParkReportYear(tParkReportYearSel,beginYear,beginYear);
                if (tParkReportYearListResult.size() > 0) {
                    for (TParkReportYear tParkReportYearDel : tParkReportYearListResult) {
                        tParkReportYearInsideService.DeleteTParkReportYear(tParkReportYearDel);
                    }
                }

                //执行插入
                TParkReportYear tParkReportYearUpdate = new TParkReportYear();
                tParkReportYearUpdate.setParkId(tParkReport.getParkId());
                tParkReportYearUpdate.setCreateDate(beginYear);
                tParkReportYearUpdate.setTempNeedpayTotal(tempNeedpayTotal);
                tParkReportYearUpdate.setTempCashActualTotal(tempCashActualTotal);
                tParkReportYearUpdate.setTempBehalfpayTotal(tempBehalfpayTotal);
                tParkReportYearUpdate.setTempQpasspayTotal(tempQpasspayTotal);
                tParkReportYearUpdate.setTempPrepayTotal(tempPrepayTotal);
                tParkReportYearUpdate.setFixAdd(fixAdd);
                tParkReportYearUpdate.setFixCashNeedTotal(fixCashNeedTotal);
                tParkReportYearUpdate.setFixCashActualTotal(fixCashActualTotal);
                tParkReportYearUpdate.setFixOnlineNeedTotal(fixOnlineNeedTotal);
                tParkReportYearUpdate.setFixOnlineActualTotal(fixOnlineActualTotal);
                tParkReportYearUpdate.setBusinessCircleTotal(businessCircleTotal);
                tParkReportYearUpdate.setFreeTotal(freeTotal);
                tParkReportYearUpdate.setTempPayDiff(tempPayDiff);
                tParkReportYearInsideService.UpdateTParkReportYear(tParkReportYearUpdate);
            }

        return null;
    }

}
