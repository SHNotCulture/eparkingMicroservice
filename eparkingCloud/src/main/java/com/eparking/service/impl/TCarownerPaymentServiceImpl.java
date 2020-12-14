package com.eparking.service.impl;

import com.common.entity.eparkingCloud.ExcelWalletRechargeRecord;
import com.common.entity.eparkingCloud.TCarownerPayment;
import com.common.entity.eparkingCloud.TCarownerPaymentCus;
import com.common.entity.eparkingCloud.TParkCar;
import com.eparking.insideService.TCarownerPaymentInsideService;
import com.eparking.insideService.TParkCarInsideService;
import com.eparking.service.TCarownerPaymentService;
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
 * @author 谢轩然
 * @Description: TCarownerPaymentService接口实现类
 * @date 2020/09/11 16:29
 */
@Service
public class TCarownerPaymentServiceImpl implements TCarownerPaymentService {

    private static final Logger logger = LoggerFactory.getLogger(TCarownerPaymentServiceImpl.class);

    @Autowired
    private TCarownerPaymentInsideService tCarownerPaymentInsideService;
    @Autowired
    private TParkCarInsideService tParkCarInsideService;


    /**
     * 查询tCarownerPayment(分页)
     *
     * @param tCarownerPaymentCus
     * @param page
     * @param limit
     * @return
     */
    public PageBean<TCarownerPayment> getTCarownerPaymentbyPage(TCarownerPaymentCus tCarownerPaymentCus, Integer page, Integer limit) {
        return tCarownerPaymentInsideService.getTCarownerPaymentbyPage(tCarownerPaymentCus, page, limit);
    }

    /**
     * 查询tCarownerPayment
     *
     * @param tCarownerPayment
     * @return
     */
    public List<TCarownerPayment> getTCarownerPayment(TCarownerPayment tCarownerPayment, String beginDate, String endDate) {
        return tCarownerPaymentInsideService.getTCarownerPayment(tCarownerPayment, beginDate, endDate);
    }

    /**
     * 更新tCarownerPayment
     *
     * @param tCarownerPayment
     * @return
     */
    public String UpdateTCarownerPayment(TCarownerPayment tCarownerPayment) {
        return tCarownerPaymentInsideService.UpdateTCarownerPayment(tCarownerPayment);
    }

    /**
     * 删除tCarownerPayment
     *
     * @param tCarownerPayment
     * @return
     */
    public String DeleteTCarownerPayment(TCarownerPayment tCarownerPayment) {
        return tCarownerPaymentInsideService.DeleteTCarownerPayment(tCarownerPayment);
    }

    /**
     * 根据ID查询tCarownerPayment
     *
     * @param id
     * @return
     */
    public TCarownerPayment getTCarownerPaymentByID(Integer id) {
        return tCarownerPaymentInsideService.getTCarownerPaymentByID(id);
    }


    @Override
    public void exportList(TCarownerPayment tCarownerPayment, String title, String beginDate, String endDate, HttpServletResponse response) {
        List<TCarownerPayment> tCarownerPaymentList = getTCarownerPayment(tCarownerPayment, beginDate, endDate);
        List<ExcelWalletRechargeRecord> excelWalletRechargeRecordList = new ArrayList<>();
        //当查找记录返回无数据时初始化ExcelWalletRechargeRecord防止数组越界
        if (tCarownerPaymentList.size() <= 0) {
            ExcelWalletRechargeRecord excelWalletRechargeRecord = new ExcelWalletRechargeRecord();
            excelWalletRechargeRecordList.add(excelWalletRechargeRecord);
        } else {
            for (TCarownerPayment carownerPayment : tCarownerPaymentList) {
                ExcelWalletRechargeRecord excelWalletRechargeRecord = new ExcelWalletRechargeRecord();
                try {
                    excelWalletRechargeRecord = (ExcelWalletRechargeRecord) BeanCopyUtil.CopyBeanToBean(carownerPayment, excelWalletRechargeRecord);
                    excelWalletRechargeRecord.setDifference();
                    TParkCar tParkCarSel = new TParkCar();
                    tParkCarSel.setParkId(tCarownerPayment.getParkId());
                    tParkCarSel.setCarPlate(tCarownerPayment.getCarplate());
//                excelWalletRechargeRecord.setAddress(tParkCarInsideService.getTParkCar(tParkCarSel).get(0).getAddress());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                excelWalletRechargeRecordList.add(excelWalletRechargeRecord);
            }
        }
        try {
            ExportExcelUtil.exportExcel(response, title, title, title, excelWalletRechargeRecordList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
