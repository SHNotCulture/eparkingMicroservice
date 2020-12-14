package com.eparking.service.impl;

import com.common.entity.eparkingCloud.ExcelCarPayment;
import com.common.entity.eparkingCloud.TCarPayRule;
import com.common.entity.eparkingCloud.TCarPayment;
import com.eparking.insideService.TCarPayRuleInsideService;
import com.eparking.insideService.TCarPaymentInsideService;
import com.eparking.service.CarPaymentService;
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
public class CarPaymentServiceImpl implements CarPaymentService {
    private static final Logger logger = LoggerFactory.getLogger(CarPaymentServiceImpl.class);
    /*    @Autowired
        private TCarPaymentMapper tCarPaymentMapper;*/
    @Autowired
    private TCarPaymentInsideService tCarPaymentInsideService;
    @Autowired
    private TCarPayRuleInsideService tCarPayRuleInsideService;


    @Override
    public List<TCarPayment> getCarPayment(TCarPayment tCarPayment, String beginData, String endData) {
//        return tCarPaymentMapper.selectByExample(setCriteria(tCarPayment, beginData, endData));
        return tCarPaymentInsideService.getTCarPayment(tCarPayment, beginData, endData);
    }

    @Override
    public PageBean<TCarPayment> getCarPaymentByPage(TCarPayment tCarPayment, String beginData, String endData, Integer page, Integer limit) {
/*        PageHelper.startPage(page, limit,"pay_time desc");
        List<TCarPayment> tCarPaymentList = getCarPayment(tCarPayment, beginData, endData);
        Integer countNums =getCount(tCarPayment, beginData, endData);
        PageBean<TCarPayment> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tCarPaymentList);
        return pageData;*/
        return tCarPaymentInsideService.getTCarPaymentbyPage(tCarPayment, beginData, endData, page, limit);
    }

    @Override
    public void exportListMonthlyDetails(TCarPayment tCarPayment, String title, HttpServletResponse response, String beginData, String endData) {
        List<TCarPayment> tCarPayments = getCarPayment(tCarPayment, beginData, endData);
        List<ExcelCarPayment> excelCarPaymentList = new ArrayList<>();
        //当查找记录返回无数据时初始化ExcelCarPayment防止数组越界
        if (tCarPayments.size() <= 0) {
            ExcelCarPayment excelCarPayment = new ExcelCarPayment();
            excelCarPaymentList.add(excelCarPayment);
        } else {
            for (TCarPayment tCarPaymentsNew : tCarPayments) {
                ExcelCarPayment excelCarPayment = new ExcelCarPayment();
                try {
                    if (tCarPaymentsNew.getPayRule() != null) {
                        TCarPayRule tCarPayRule = new TCarPayRule();
                        tCarPayRule.setParkId(tCarPaymentsNew.getParkId());
                        tCarPayRule.setId(tCarPaymentsNew.getPayRule());
                        List<TCarPayRule> tCarPayRuleList = tCarPayRuleInsideService.getTCarPayRule(tCarPayRule);
                        if (tCarPayRuleList.size() > 0) {
                            excelCarPayment.setPayRuleStr(tCarPayRuleList.get(0).getRuleName());
                        }
                    }
//                    logger.info("tCarPaymentsNew: " + JsonUtil.beanToJson(tCarPaymentsNew));
                    excelCarPayment = (ExcelCarPayment) BeanCopyUtil.CopyBeanToBean(tCarPaymentsNew, excelCarPayment);
//                    logger.info("excelCarPayment: " + JsonUtil.beanToJson(excelCarPayment));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                excelCarPaymentList.add(excelCarPayment);
            }
        }
        try {
            ExportExcelUtil.exportExcel(response, title, title, title, excelCarPaymentList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param tCarPayment
     * @return
     */
    public String UpdateTCarPayment(TCarPayment tCarPayment) {
/*        if(tCarPayment.getId()!=null){
            tCarPaymentMapper.updateByPrimaryKeySelective(tCarPayment);
        }
        else
        {
            tCarPaymentMapper.insertSelective(tCarPayment);
        }
        return "更新成功";*/
        return tCarPaymentInsideService.UpdateTCarPayment(tCarPayment);
    }
}
