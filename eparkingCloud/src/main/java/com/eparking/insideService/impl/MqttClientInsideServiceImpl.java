package com.eparking.insideService.impl;

import com.eparking.insideService.MqttClientInsideService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @author ：lishuhan
 * @date ：Created in 2020/11/7 23:09
 * @description：
 * @modified By：
 * @version: 1.0$
 */
@Component
public class MqttClientInsideServiceImpl implements MqttClientInsideService {

    private static final Logger logger = LoggerFactory.getLogger(MqttClientInsideServiceImpl.class);

    public void SendCarFeeRequst(@RequestBody Map map){
        String str = "发送线下计费请求失败！";
        logger.info("发送线下计费请求失败！");
    }
    public void SendCalFeeCurrentCar(@RequestBody Map map){
        String str = "发送线下实时通道识别请求失败！";
        logger.info("发送线下实时通道识别请求失败！");
    }
    public void SendCouponUpdate(@RequestBody Map map){
        String str = "发送会员优惠失败！";
        logger.info("发送会员优惠失败！");
    }
    public String reservedCar(@RequestBody Map map){
        String str = "车辆预约失败！";
        logger.info("车辆预约失败！");
        return str;
    }
    public String carOwnerNosensePay(@RequestBody Map map){
        String str = "车主钱包余额支付失败！";
        logger.info("车主钱包余额支付失败！");
        return str;
    }

    @Override
    public String bindingCar(@RequestBody Map map) {
        String str = "绑定车牌下发失败！";
        logger.info("绑定车牌下发失败！");
        return str;
    }

}
