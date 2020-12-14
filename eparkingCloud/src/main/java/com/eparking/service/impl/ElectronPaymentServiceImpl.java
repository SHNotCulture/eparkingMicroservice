package com.eparking.service.impl;

import com.common.entity.eparkingCloud.ExcelElectronPayment;
import com.common.entity.eparkingCloud.TElectronPayment;
import com.eparking.insideService.ElectronPaymentServiceInsideService;
import com.eparking.service.ElectronPaymentService;
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
 * @Date Create in 18:152018-11-6
 * @Modified By:
 */
@Service
public class ElectronPaymentServiceImpl implements ElectronPaymentService {
    private static final Logger logger = LoggerFactory.getLogger(ElectronPaymentServiceImpl.class);
    /*    @Autowired
        private TElectronPaymentMapper tElectronPaymentMapper;*/
    @Autowired
    private ElectronPaymentServiceInsideService electronPaymentServiceInsideService;

    /**
     * 设置查询条件
     * @param tElectronPayment
     * @return
     */
/*    private TElectronPaymentCriteria setCriteria(String beginTime, String endTime, TElectronPayment tElectronPayment){
        //查询全部车场信息
        TElectronPaymentCriteria tElectronPaymentCriteria= new TElectronPaymentCriteria();
        if(tElectronPayment!=null){
            TElectronPaymentCriteria.Criteria criteria=tElectronPaymentCriteria.createCriteria();
            if(tElectronPayment.getParkId()!=null)
            {
                criteria.andParkIdEqualTo(tElectronPayment.getParkId());
            }
            if(tElectronPayment.getCheckFlag()!=null)
            {
                criteria.andCheckFlagEqualTo(tElectronPayment.getCheckFlag());
            }
            if(beginTime!=null&&endTime!=null &&beginTime!=""&&endTime!="")
            {
                criteria.andCreateDateBetween(beginTime,endTime);
            }

        }
        return  tElectronPaymentCriteria;
    }*/
    /**
     * 获取数据总量
     * @param tElectronPayment
     * @return
     */
/*    private Integer getCount(String beginTime, String endTime, TElectronPayment tElectronPayment){
        Integer total =(int)tElectronPaymentMapper.countByExample(setCriteria(beginTime,endTime,tElectronPayment));
        return total;
    }*/

    /**
     * 查询电子对账报表信息(分页)
     *
     * @param beginTime
     * @param endTime
     * @param tElectronPayment
     * @param page
     * @param limit
     * @return
     */
    public PageBean<TElectronPayment> getElectronPaymentbyPage(TElectronPayment tElectronPayment, String beginTime, String endTime, Integer page, Integer limit) {
/*        PageHelper.startPage(page, limit,"id desc");
        List<TElectronPayment> tElectronPayments=getElectronPayment(beginTime,endTime,tElectronPayment);
        Integer countNums =getCount(beginTime,endTime,tElectronPayment);
        PageBean<TElectronPayment> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tElectronPayments);
        return pageData;*/
        return electronPaymentServiceInsideService.getTElectronPaymentbyPage(tElectronPayment, beginTime, endTime, page, limit);
    }

    /**
     * 查询电子对账报表信息
     *
     * @return
     */
    public List<TElectronPayment> getElectronPayment(String beginTime, String endTime, TElectronPayment tElectronPayment) {
/*        List<TElectronPayment> tElectronPaymentList=tElectronPaymentMapper.selectByExample(setCriteria(beginTime,endTime,tElectronPayment));
        return tElectronPaymentList;*/
        return electronPaymentServiceInsideService.getTElectronPaymentList(tElectronPayment, beginTime, endTime);
    }

    /**
     * 更新电子对账单信息
     *
     * @param tElectronPayment
     * @return
     */
    public String UpdateElectronPayment(TElectronPayment tElectronPayment) {
/*        logger.info(tElectronPayment.toString());
        if(tElectronPayment.getId()!=null){
            tElectronPaymentMapper.updateByPrimaryKeySelective(tElectronPayment);
        }
        else
        {
            tElectronPaymentMapper.insertSelective(tElectronPayment);
        }
        return "更新成功";*/
        return electronPaymentServiceInsideService.UpdateTElectronPayment(tElectronPayment);
    }

    /**
     * 导出电子对账单信息
     *
     * @param beginTime
     * @param endTime
     * @param tElectronPayment
     * @param title
     * @param response
     */
    public void exportList(String beginTime, String endTime, TElectronPayment tElectronPayment, String title, HttpServletResponse response) {
        List<TElectronPayment> tElectronPayments = getElectronPayment(beginTime, endTime, tElectronPayment);
        List<ExcelElectronPayment> excelElectronPayments = new ArrayList<>();
        //当查找记录返回无数据时初始化ExcelElectronPayment防止数组越界
        if (tElectronPayments.size() <= 0) {
            ExcelElectronPayment excelElectronPayment = new ExcelElectronPayment();
            excelElectronPayments.add(excelElectronPayment);
        } else {
            for (TElectronPayment tElectronPaymentNew : tElectronPayments) {
                ExcelElectronPayment excelElectronPayment = new ExcelElectronPayment();
                try {
                    excelElectronPayment = (ExcelElectronPayment) BeanCopyUtil.CopyBeanToBean(tElectronPaymentNew, excelElectronPayment);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                excelElectronPayments.add(excelElectronPayment);
            }
        }
//        logger.info("tElectronPayments:" + tElectronPayments);
        try {
            ExportExcelUtil.exportExcel(response, title, title, title, excelElectronPayments);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
