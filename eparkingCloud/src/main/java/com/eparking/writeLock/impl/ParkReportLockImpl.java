package com.eparking.writeLock.impl;

import com.common.entity.eparkingCloud.TCompanyPark;
import com.eparking.writeLock.ParkReportLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkReportLockImpl extends BaseLockImpl<TCompanyPark> implements ParkReportLock {
    private static Logger logger = LoggerFactory.getLogger(ParkReportLockImpl.class);
    @Override
    public void setTParkReport(TCompanyPark tCompanyPark) {
        boolean flag =true;
        if(containsKey(tCompanyPark.getId().toString())){
            logger.info("已获取到车场,车场ID："+tCompanyPark.getId());
            flag=false;
        }
        if(flag){
            put(tCompanyPark.getId().toString(),tCompanyPark);
            logger.info("保存车场,"+tCompanyPark);
        }
    }

    @Override
    public List<TCompanyPark> getTParkReport() {
        List<TCompanyPark> tCompanyParkList=new ArrayList<>();
        Integer num=100;
        if(allValues().size()>0) {
            for(TCompanyPark value : allValues()){
                tCompanyParkList.add(value);
                remove(value.getId().toString());
                num--;
                if(num<=0){
                    break;
                }
            }
            logger.info("获取车场,数量："+tCompanyParkList.size());
        }
        return  tCompanyParkList;
    }
}
