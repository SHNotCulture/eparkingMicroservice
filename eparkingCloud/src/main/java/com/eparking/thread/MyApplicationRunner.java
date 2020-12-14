package com.eparking.thread;

import com.common.entity.eparkingCloud.TCarPayRule;
import com.common.entity.eparkingCloud.TCompanyPark;
import com.eparking.service.CarParkService;
import com.eparking.service.CarPayRuleService;
import com.eparking.util.SpringUtils;
import com.eparking.writeLock.CarParkLock;
import com.eparking.writeLock.CarPayRuleLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyApplicationRunner implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(MyApplicationRunner.class);
    @Autowired
    private CarPayRuleService carPayRuleService;
    @Autowired
    private CarPayRuleLock carPayRuleLock;
    @Autowired
    private CarParkService carParkService;
    @Autowired
    private CarParkLock carParkLock;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //登记车规则存储redis
        carPayRuleService = SpringUtils.getBean(CarPayRuleService.class);
        carPayRuleLock = SpringUtils.getBean(CarPayRuleLock.class);
        TCarPayRule tCarPayRule = new TCarPayRule();
        List<TCarPayRule> tCarPayRuleList = carPayRuleService.getCarPayRule(tCarPayRule);
        if(tCarPayRuleList.size()>0){
            for(TCarPayRule tCarPayRuleOne:tCarPayRuleList){
                carPayRuleLock.setTCarPayRule(tCarPayRuleOne);
            }
        }
        logger.info("执行 运行时存储所有登记车规则");

        //停车场信息存储redis
        carParkService = SpringUtils.getBean(CarParkService.class);
        carParkLock = SpringUtils.getBean(CarParkLock.class);
        TCompanyPark tCompanyPark = new TCompanyPark();
        List<TCompanyPark> tCompanyParkList = carParkService.getCarPark(tCompanyPark);
        if(tCompanyParkList.size()>0){
            for(TCompanyPark tCompanyParkOne:tCompanyParkList){
                carParkLock.setTCompanyPark(tCompanyParkOne);
            }
        }
        logger.info("执行 运行时存储所有车场信息");
    }
}
