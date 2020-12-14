package com.eparking.service.impl;

import com.common.entity.eparkingCloud.ExcelTParkDuty;
import com.common.entity.eparkingCloud.TParkDuty;
import com.eparking.insideService.TParkDutyInsideService;
import com.eparking.service.CashPaymentService;
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

@Service
public class CashPaymentServiceImpl implements CashPaymentService {
    private static final Logger logger = LoggerFactory.getLogger(CashPaymentServiceImpl.class);

    /*    @Autowired
        private TParkDutyMapper tParkDutyMapper;*/
    @Autowired
    private TParkDutyInsideService tParkDutyInsideService;


    /**
     *设置查询条件
     * @param tParkDuty
     * @param beginTime
     * @param endTime
     * @return
     */
/*    private TParkDutyCriteria setCriteria(TParkDuty tParkDuty,String beginTime, String endTime){
        TParkDutyCriteria tParkDutyCriteria=new TParkDutyCriteria();
        if(tParkDuty != null ){
            TParkDutyCriteria.Criteria criteria=tParkDutyCriteria.createCriteria();
            if(tParkDuty.getParkId()!=null){
                criteria.andParkIdEqualTo(tParkDuty.getParkId());
            }
            if(tParkDuty.getDutyPerson() !=null && !tParkDuty.getDutyPerson().equals("")) {
                criteria.andDutyPersonEqualTo(tParkDuty.getDutyPerson());
            }
            if(beginTime !=null && !beginTime.equals("")){
                criteria.aneparkingdbeginTimeGreaterThanOrEqualTo(beginTime);
            }
            if(endTime != null && !endTime.equals("")){
                criteria.aneparkingdbeginTimeLessThanOrEqualTo(endTime);
            }

        }
        return  tParkDutyCriteria;
    }*/

    /**
     *获取数据总量
     * @param tParkDuty
     * @param beginTime
     * @param endTime
     * @return
     */
/*    private Integer getCount(TParkDuty tParkDuty,String beginTime, String endTime){
        Integer total =(int)tParkDutyMapper.countByExample(setCriteria(tParkDuty,beginTime,endTime));
        return total;
    }*/

    /**
     * 查询现金对账记录（分页）
     *
     * @param tParkDuty
     * @param beginTime
     * @param endTime
     * @param page
     * @param limit
     * @return
     */
    public PageBean<TParkDuty> getTParkDutyListbyPage(TParkDuty tParkDuty, String beginTime, String endTime, Integer page, Integer limit) {
/*        PageHelper.startPage(page, limit,"id desc");
        List<TParkDuty> tParkDuties=getTParkDutyList(tParkDuty,beginTime,endTime);
        Integer countNums =getCount(tParkDuty,beginTime,endTime);
        PageBean<TParkDuty> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tParkDuties);
        return pageData;*/
        return tParkDutyInsideService.getTParkDutybyPage(tParkDuty, beginTime, endTime, page, limit);
    }

    /**
     * 查询现金对账记录
     *
     * @param tParkDuty
     * @param beginTime
     * @param endTime
     * @return
     */
    @Override
    public List<TParkDuty> getTParkDutyList(TParkDuty tParkDuty, String beginTime, String endTime) {
/*        List<TParkDuty> tParkDutyList=tParkDutyMapper.selectByExample(setCriteria(tParkDuty,beginTime,endTime));
        return tParkDutyList;*/
        return tParkDutyInsideService.getTParkDuty(tParkDuty, beginTime, endTime);
    }

    /**
     * 导出电子对账单信息
     *
     * @param beginTime
     * @param endTime
     * @param tParkDuty
     * @param title
     * @param response
     */
    public void exportList(String beginTime, String endTime, TParkDuty tParkDuty, String title, HttpServletResponse response) {
        List<TParkDuty> tParkDuties = getTParkDutyList(tParkDuty, beginTime, endTime);
        List<ExcelTParkDuty> excelTParkDuties = new ArrayList<>();
//        logger.info("tParkDuty:"+tParkDuty);
        //当查找记录返回无数据时初始化ExcelTParkDuty防止数组越界
        if (tParkDuties.size() <= 0) {
            ExcelTParkDuty excelTParkDuty = new ExcelTParkDuty();
            excelTParkDuties.add(excelTParkDuty);
        } else {
            for (TParkDuty tParkDutyNew : tParkDuties) {
                ExcelTParkDuty excelTParkDuty = new ExcelTParkDuty();
                try {
                    excelTParkDuty = (ExcelTParkDuty) BeanCopyUtil.CopyBeanToBean(tParkDutyNew, excelTParkDuty);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                excelTParkDuties.add(excelTParkDuty);
            }
        }
        try {
            ExportExcelUtil.exportExcel(response, title, title, title, excelTParkDuties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


