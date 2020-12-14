package com.eparking.writeLock.impl;

import com.common.entity.eparkingCloud.TCompanyPark;
import com.eparking.writeLock.CarParkLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarParkLockImpl extends BaseLockImpl<TCompanyPark> implements CarParkLock {
    private static Logger logger = LoggerFactory.getLogger(CarParkLockImpl.class);

    @Override
    public void setTCompanyPark(TCompanyPark tCompanyPark) {
        boolean flag =true;
        if(containsKey(tCompanyPark.getId().toString())){
            flag=false;
        }
        if(flag){
            put(tCompanyPark.getId().toString(),tCompanyPark);
        }
    }

    @Override
    public List<TCompanyPark> getTCompanyPark() {
        List<TCompanyPark> tCompanyParkList=new ArrayList<>();
        if(allValues().size()>0) {
            for(TCompanyPark value : allValues()){
                tCompanyParkList.add(value);
            }
        }
        return  tCompanyParkList;
    }
}
