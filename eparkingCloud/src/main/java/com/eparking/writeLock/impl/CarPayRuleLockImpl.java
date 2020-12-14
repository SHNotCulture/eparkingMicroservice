package com.eparking.writeLock.impl;

import com.common.entity.eparkingCloud.TCarPayRule;
import com.eparking.writeLock.CarPayRuleLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarPayRuleLockImpl extends BaseLockImpl<TCarPayRule> implements CarPayRuleLock {
    private static Logger logger = LoggerFactory.getLogger(CarPayRuleLockImpl.class);

    @Override
    public void setTCarPayRule(TCarPayRule tCarPayRule) {
        boolean flag =true;
        if(containsKey(tCarPayRule.getId().toString())){
            flag=false;
        }
        if(flag){
            put(tCarPayRule.getId().toString(),tCarPayRule);
        }
    }

    @Override
    public List<TCarPayRule> getTCarPayRule() {
        List<TCarPayRule> tCarPayRuleList=new ArrayList<>();
        if(allValues().size()>0) {
            for(TCarPayRule value : allValues()){
                tCarPayRuleList.add(value);
            }
        }
        return  tCarPayRuleList;
    }
}
