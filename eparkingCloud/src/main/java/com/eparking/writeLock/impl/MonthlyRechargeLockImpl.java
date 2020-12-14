package com.eparking.writeLock.impl;

import com.common.entity.eparkingCloud.TBusine;
import com.eparking.writeLock.MonthlyRechargeLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MonthlyRechargeLockImpl extends BaseLockImpl<TBusine> implements MonthlyRechargeLock {
    private static Logger logger = LoggerFactory.getLogger(MonthlyRechargeLockImpl.class);

    @Override
    public void setTheBusineRechage(TBusine tBusine) {
        boolean flag =true;
        if(containsKey(tBusine.getId().toString())){
            logger.info("已获取到自动充值商户,商户ID："+tBusine.getId()+",parkId:"+tBusine.getParkId());
            flag=false;
        }
        if(flag){
                put(tBusine.getId().toString(),tBusine);
                logger.info("保存有权限自动充值商户,"+tBusine);
            }
    }

    @Override
    public List<TBusine> getTheBusineRechage() {
        List<TBusine> tBusineList=new ArrayList<>();
        Integer num=100;
        if(allValues().size()>0) {
            for(TBusine value : allValues()){
                tBusineList.add(value);
                num--;
                if(num<=0){
                    break;
                }
                remove(value.getId().toString());
            }
            logger.info("获取自动充值商户,数量："+tBusineList.size());
        }
        return  tBusineList;
    }
}
