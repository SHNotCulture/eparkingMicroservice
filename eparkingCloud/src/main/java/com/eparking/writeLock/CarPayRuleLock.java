package com.eparking.writeLock;

import com.common.entity.eparkingCloud.TCarPayRule;

import java.util.List;

public interface CarPayRuleLock  extends BaseLock<TCarPayRule> {
    void setTCarPayRule(TCarPayRule tCarPayRule);
    List<TCarPayRule> getTCarPayRule();
}
