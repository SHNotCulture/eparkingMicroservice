package com.eparking.service.impl;


import com.common.entity.eparkingCloud.TBusinePay;
import com.eparking.service.BusinePayService;
import com.eparking.service.MerchantRechargeRecordService;
import com.common.entity.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**@Author xiexuanran
 * @Date  2019/4/24
 */

@Service
public class MerchantRechargeRecordServiceImpl implements MerchantRechargeRecordService {
/*    @Autowired
    private TBusinePayMapper tBusinePayMapper;
    @Autowired
    private TBusineMapper tBusineMapper;*/
    @Autowired
    private BusinePayService businePayService;

    public List<TBusinePay> findMerchantPay(TBusinePay tBusinePay,Integer busineId, String PayTimeBegin, String PayTimeEnd){
//        return tBusinePayMapper.selectByExample(getTBusinePayCriteria(tBusinePay,busineId,PayTimeBegin,PayTimeEnd));
        return businePayService.getTBusinePay(tBusinePay, busineId, PayTimeBegin, PayTimeEnd);
    }

/*    public TBusinePayCriteria getTBusinePayCriteria (TBusinePay tBusinePay,Integer busineId, String PayTimeBegin, String PayTimeEnd){
        TBusinePayCriteria tBusinePayCriteria = new TBusinePayCriteria();
        TBusinePayCriteria.Criteria criteria = tBusinePayCriteria.createCriteria();
        criteria.aneparkingdbusineIdEqualTo(busineId);
        if (tBusinePay.getParkId()!=null){
            criteria.andParkIdEqualTo(tBusinePay.getParkId());
        }
        if (PayTimeBegin!=null && PayTimeBegin!=""  && PayTimeEnd!="" && PayTimeEnd!=null ){
            criteria.andPayTimeBetween(PayTimeBegin,PayTimeEnd);
        }
        return tBusinePayCriteria;
    }*/



    /**
     * 获取数据总量
     * @param tBusinePay
     * @param
     * @return
     */
/*    private Integer getCount(TBusinePay tBusinePay,Integer busineId, String PayTimeBegin, String PayTimeEnd){
        Integer total = (int)tBusinePayMapper.countByExample(getTBusinePayCriteria(tBusinePay,busineId,PayTimeBegin,PayTimeEnd));
        return total;
    }*/

    /**
     * 商户优惠(分页)
     * @param tBusinePay
     * @param
     * @param page
     * @param limit
     * @return
     */

    @Override
    public PageBean<TBusinePay> findMerchantRechargeRecoreparkingdbyPage(TBusinePay tBusinePay ,Integer busineId, String PayTimeBegin, String PayTimeEnd, Integer page, Integer limit){
/*        PageHelper.startPage(page, limit,"pay_time desc");
        List<TBusinePay> tBusinePays=findMerchantPay(tBusinePay,busineId,PayTimeBegin,PayTimeEnd);
        Integer countNums =getCount(tBusinePay,busineId,PayTimeBegin,PayTimeEnd);
        PageBean<TBusinePay> pageData = new PageBean<>(page, limit, countNums);
        pageData.setItems(tBusinePays);
        return pageData;*/
        return businePayService.getTBusinePaybyPage(tBusinePay,busineId,page,limit,PayTimeBegin,PayTimeEnd);
    }

}
