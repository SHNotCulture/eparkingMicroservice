package com.eparking.service.impl;

import com.common.entity.eparkingCloud.ExcelTBusinePay;
import com.common.entity.eparkingCloud.TBusinePay;
import com.eparking.insideService.TBusinePayInsideService;
import com.eparking.service.BusinePayService;
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
 * @Date Create in 11:162018-10-9
 * @Modified By:
 */
@Service
public class BusinePayServiceImpl implements BusinePayService {
    private static final Logger logger = LoggerFactory.getLogger(BusinePayServiceImpl.class);
    /*    @Autowired
        private TBusinePayMapper tBusinePayMapper;*/
    @Autowired
    private TBusinePayInsideService tBusinePayInsideService;


    /**
     * 查询商户充值记录(分页)
     *
     * @param tBusinePay
     * @param page
     * @param limit
     * @return
     */
    public PageBean<TBusinePay> getTBusinePaybyPage(TBusinePay tBusinePay, Integer busineId, Integer page, Integer limit, String beginTime, String endTime) {
/*        PageHelper.startPage(page, limit,"id desc");
        List<TBusinePay> tBusinePayList=getTBusinePay(tBusinePay,beginTime,endTime);
        Integer countNums =getCount(tBusinePay,beginTime,endTime);
        PageBean<TBusinePay> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tBusinePayList);
        return pageData;*/
        return tBusinePayInsideService.getTBusinePaybyPage(tBusinePay, busineId, beginTime, endTime, page, limit);
    }

    /**
     * 查询商户充值记录
     *
     * @return
     */
    public List<TBusinePay> getTBusinePay(TBusinePay tBusinePay, Integer busineId, String beginTime, String endTime) {
//        List<TBusinePay> tBusinePayList=tBusinePayMapper.selectByExample(setCriteria(tBusinePay,beginTime,endTime));
        List<TBusinePay> tBusinePayList = tBusinePayInsideService.getTBusinePay(tBusinePay, busineId, beginTime, endTime);
        return tBusinePayList;
    }

    /**
     * 导出商户充值记录
     *
     * @param beginTime
     * @param endTime
     * @param tBusinePay
     * @param title
     * @param response
     */
    public void exportList(TBusinePay tBusinePay, Integer busineId, String beginTime, String endTime, String title, HttpServletResponse response) {
        List<TBusinePay> tBusinePayList = getTBusinePay(tBusinePay, busineId, beginTime, endTime);
        List<ExcelTBusinePay> excelTBusinePays = new ArrayList<>();
//        logger.info("tBusinePayList:"+tBusinePayList);
        //当查找记录返回无数据时初始化ExcelTBusinePay防止数组越界
        if (tBusinePayList.size() <= 0) {
            ExcelTBusinePay excelTBusinePay = new ExcelTBusinePay();
            excelTBusinePays.add(excelTBusinePay);
        } else {
            for (TBusinePay tBusinePayNew : tBusinePayList) {
                ExcelTBusinePay excelTBusinePay = new ExcelTBusinePay();
                try {
                    excelTBusinePay = (ExcelTBusinePay) BeanCopyUtil.CopyBeanToBean(tBusinePayNew, excelTBusinePay);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                excelTBusinePays.add(excelTBusinePay);
            }
        }
        try {
            ExportExcelUtil.exportExcel(response, title, title, title, excelTBusinePays);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Double totalRecharge(Integer busineId) {
        return null;
    }

    @Override
    public Double totalConsumption(Integer busineId) {
        return null;
    }

}
